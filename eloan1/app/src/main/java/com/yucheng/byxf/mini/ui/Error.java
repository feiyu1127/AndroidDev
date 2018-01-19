package com.yucheng.byxf.mini.ui;

public class Error {
	public static final int ERR_FIND=0x80;
	public static final int RC_SUCCESS=0x90;
	public static final int FIND_SUCCESS=0x9F;
	public static final int ERR_PORT=0x01;
	public static final int ERR_TIMEOUT=0x02;
	public static final int ERR_PCCHECKSUM=0x03;
	public static final int ERR_INVALIDLENGTH=0xF1;
	public static final int ERR_FILE=0xF3;

	public static String GetErrorText(int result){
		if(result<1)
			return "相片解码失败:"+Integer.toString(result);
		switch(result){
		case 1:
			return "端口打开失败";
		case 2:
			return "PC接收数据超时";
		case 3:
			return "PC判断校验和错";
		case 5:
			return "SAM串口不可用";
		case 0x10:
			return "SAM判断校验和错";
		case 0x11:
			return "SAM接收超时";
		case 0x21:
			return "接收业务终端的命令错误";
		case 0x23:
			return "越权操作";
		case 0x24:
			return "无法识别的错误";
		case 0x31:
			return "卡认证机具失败";
		case 0x32:
			return "机具认证卡失败";
		case 0x33:
			return "信息验证错误";
		case 0x34:
			return "尚未找卡，不能进行对卡的操作";
		case 0x40:
			return "无法识别的卡类型";
		case 0x41:
			return "读卡操作失败";
		case 0x47:
			return "取随机数失败";
		case 0x60:
			return "自检失败，不能接收命令";
		case 0x66:
			return "SAM没经过授权,无法使用";
		case 0x80:
			return "寻卡失败";
		case 0x81:
			return "选卡失败";
		case 0x90:
			return "操作成功";
		case 0x91:
			return "卡中此项无内容";
		case 0x9F:
			return "寻卡成功";
		case ERR_INVALIDLENGTH:
			return "应答长度错误";
		case ERR_FILE:
			return "读写文件失败";
		default:
		    return "未知错误";
		}
	}
}