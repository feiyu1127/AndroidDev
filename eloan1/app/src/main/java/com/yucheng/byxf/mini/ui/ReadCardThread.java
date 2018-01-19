package com.yucheng.byxf.mini.ui;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;

public class ReadCardThread extends Thread {
	static final int MAXACKLEN=1295;
	static final byte[]cmdFind={
		(byte)0xAA,(byte)0xAA,(byte)0xAA,(byte)0x96,(byte)0x69,
		(byte)0x00,(byte)0x03,(byte)0x20,(byte)0x01,(byte)0x22};
	static final byte[] cmdSelect={
		(byte)0xAA,(byte)0xAA,(byte)0xAA,(byte)0x96,(byte)0x69,
		(byte)0x00,(byte)0x03,(byte)0x20,(byte)0x02,(byte)0x21};
	static final byte[] cmdRead={
		(byte)0xAA,(byte)0xAA,(byte)0xAA,(byte)0x96,(byte)0x69,
		(byte)0x00,(byte)0x03,(byte)0x30,(byte)0x01,(byte)0x32};
	public static final int WM_CLEARSCREEN=1;
	public static final int WM_READCARD=2;
	public static final int WM_ERROR=3;

	volatile int workStatus=0;
	Context mContext=null;
	Handler mHandler=null;
	BluetoothDevice mDevice=null;
	BluetoothSocket mSocket=null;
	InputStream mInputStream=null;
	OutputStream mOutputStream=null;
	int mLenReaded=0;
	
	public ReadCardThread(Context context, Handler handler, BluetoothDevice device){
		mContext=context;
		mHandler=handler;
		mDevice=device;
		workStatus=1;
	}
	
	public void StopReadCard(){
		workStatus=0;
		try {
			join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		try {
			mSocket=mDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
			mSocket.connect();
			mInputStream=mSocket.getInputStream();
			mOutputStream=mSocket.getOutputStream();
			//btClear();
			readCard();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			mHandler.obtainMessage(WM_ERROR,0,Error.ERR_PORT);
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeAll();
	}
	
	public void cancel(){
		closeAll();
	}
	
	void closeAll(){
		mHandler=null;
		mDevice=null;
		try {
			if(mInputStream!=null){
				mInputStream.close();
				mInputStream=null;
			}
			if(mOutputStream!=null){
				mOutputStream.close();
				mOutputStream=null;
			}
			if(mSocket!=null){
				mSocket.close();
				mSocket=null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	boolean isAckValid(byte[]ack){
		final byte[] head={(byte)0xAA,(byte)0xAA,(byte)0xAA,(byte)0x96,(byte)0x69};
		
		for(int i=0;i<head.length;i++){
			if(ack[i]!=head[i])
				return false;
		}
		return true;
	}
	
	byte calcXor(byte[]src, int offset, int len){
		byte data=0;
		
		for(int i=0;i<len;i++){
			data^=src[offset+i];
		}
		return data;
	}
	
	/*void displayError(String text){
		mHandler.obtainMessage(10, text).sendToTarget();
	}
	
	void displayError2(String text){
		mHandler.obtainMessage(11, text).sendToTarget();
	}*/
	
	int btRead(byte[]data, int offset, int lenToRead) throws IOException, InterruptedException{
		int result;
		int i=0;
		int timeout=200;
		
		do{
			if(mInputStream.available()>0){
				result=mInputStream.read(data,offset+i,lenToRead-i);
				if(result>0){
					i+=result;
					if(i>=lenToRead)
						break;
				}
			}
			if(timeout==0){
				mLenReaded=i;
				//displayError2("#21 Timeout Receive "+ Integer.toString(i)+" Byte");
				return Error.ERR_TIMEOUT;
			}
			timeout--;
			Thread.sleep(10);
		}while(true);
		mLenReaded=i;
		return Error.RC_SUCCESS;
	}
	
	void btClear() throws IOException, InterruptedException{
		//int result;
		byte[]temp=new byte[16];
		
		while(mInputStream.available()>0){
			mInputStream.read(temp);
			//displayError("#20 Drop "+Integer.toString(result)+" Byte");
			Thread.sleep(10);
		}
	}
	
	int SendCmd(byte[]cmd,byte[]ack,Integer dataLen) throws IOException, InterruptedException{
		int result;
		int len;
		
		mOutputStream.write(cmd);
		mOutputStream.flush();
		//Thread.sleep(20);
		result=btRead(ack, 0, 7);
		if(result!=Error.RC_SUCCESS){
			//displayError("#1 Receive "+Integer.toString(mLenReaded)+" Byte");
			return Error.ERR_TIMEOUT;
		}
		if(!isAckValid(ack)){
			//displayError("#2 Package head error");
			return Error.ERR_TIMEOUT;
		}
		len=ack[5]<<8|ack[6];
		if(len<4 || len+7>MAXACKLEN){
			//displayError("#3 Package length error");
			return Error.ERR_INVALIDLENGTH;
		}
		result=btRead(ack,7,len);
		if(result!=Error.RC_SUCCESS){
			//displayError("#4 Receive "+Integer.toString(mLenReaded)+" Byte");
			return Error.ERR_TIMEOUT;
		}
		if(calcXor(ack,5,len+2)!=0){
			//displayError("#5 Checksum error");
			return Error.ERR_PCCHECKSUM;
		}
		dataLen=len-4;
		result=ack[9]& 0xFF;
		Thread.sleep(10);
		btClear();
		return result;
	}
	
	void readCard() throws IOException, InterruptedException{
		int newResult=Error.RC_SUCCESS;
		int oldResult=Error.RC_SUCCESS;
		Integer dataLen=new Integer(0);
		byte[]ack=new byte[1296];
		PersonInfo person=null;
		
		File wltFile=mContext.getFileStreamPath("image.wlt");
        File bmpFile=mContext.getFileStreamPath("image.bmp");
        String wltPath=wltFile.getAbsolutePath();
        String bmpPath=bmpFile.getAbsolutePath();
        FileOutputStream fos=null;
        DecodeWlt dw=new DecodeWlt();
        
		while(workStatus==1){
			if(newResult!=oldResult){
				if(newResult!=Error.RC_SUCCESS){
					mHandler.obtainMessage(WM_ERROR, 0, newResult).sendToTarget();
				}
				oldResult=newResult;
			}
			Thread.sleep(500);
			newResult=SendCmd(cmdFind,ack,dataLen);
			if(newResult!=Error.FIND_SUCCESS)
				continue;
			mHandler.obtainMessage(WM_CLEARSCREEN).sendToTarget();
			newResult=SendCmd(cmdSelect,ack,dataLen);
			if(newResult!=Error.RC_SUCCESS)
				continue;
			newResult=SendCmd(cmdRead,ack,dataLen);
			if(newResult!=Error.RC_SUCCESS)
				continue;
			person=PersonInfo.Parse(ack,14);
			
			fos=new FileOutputStream(wltFile);
			fos.write(ack, 14+256, 1024);
			fos.close();
			newResult = dw.Wlt2Bmp(wltPath, bmpPath);
			if(newResult==1){
				newResult=Error.RC_SUCCESS;
				person.photo=BitmapFactory.decodeFile(bmpPath);
				//if(person.photo!=null)
				//	person.photo.setDensity(96);
			}
			mHandler.obtainMessage(WM_READCARD, person).sendToTarget();
		}
	}
}