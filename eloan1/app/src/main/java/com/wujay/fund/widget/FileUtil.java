package com.wujay.fund.widget;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.util.EncodingUtils;

public class FileUtil {
    String res;
	//读文件
    public String readSDFile(String fileName) throws IOException {    
      
            File file = new File(fileName);    
      
            FileInputStream fis = new FileInputStream(file);    
      
            int length = fis.available();   
      
             byte [] buffer = new byte[length];   
             fis.read(buffer);       
      
             res = EncodingUtils.getString(buffer, "UTF-8");   
      
             fis.close();       
             return res;    
    }    
      
    //写文件  
    public void writeSDFile(String fileName, String write_str) throws IOException{    
      
            File file = new File(fileName);    
      
            FileOutputStream fos = new FileOutputStream(file);    
      
            byte [] bytes = write_str.getBytes();   
      
            fos.write(bytes);   
      
            fos.close();   
    }   
	
	
}
