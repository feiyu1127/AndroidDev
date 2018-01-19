package com.yucheng.byxf.mini.utils;


/**
 * 根据卡号和密码计算pinblock
 * 
 * @author neo
 *
 */
public class PINUtils {
	
	/**
	 * 根据卡号和密码计算pinblock
	 * 
	 * @param cardNo 卡号
	 * 
	 * @param pwd 密码
	 * 
	 * @return pinblock
	 */
	public static String computePinBlock(String cardNo,String pwd) {
		String pinblock = null;
		if (cardNo==null||!cardNo.matches("\\d{16,19}")||pwd==null||!pwd.matches("\\d{6}")) {
			return pinblock;
		}
		String pwdBlock = computePasswordPinBlock(pwd);
		String cardNoBlock = computeCardNoPinBlock(cardNo);
		pinblock = xor(pwdBlock,cardNoBlock);
		return pinblock;
	}
	
	/**
	 * 按位异或
	 * 
	 * @param pwdBlock 密码块 16个字节
	 * 
	 * @param cardNoBlock cardNo块 16个字节
	 * 
	 * @return
	 */
	private static String xor(String pwdBlock, String cardNoBlock) {
		int pwdLength = pwdBlock.length();
		int cardNoLength = cardNoBlock.length();
		if (pwdLength!=cardNoLength) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		char hexj,hexk;
		int intj=0,intk=0;
		for (int i = 0; i < pwdLength; i++) {
			hexj = pwdBlock.charAt(i);
			hexk = cardNoBlock.charAt(i);
			intj = hex2int(hexj);
			intk = hex2int(hexk);
//			System.out.println(intj+"^"+intk+"："+Integer.toHexString(intj^intk));
			builder.append(Integer.toHexString(intj^intk));
		}
		return builder.toString().toUpperCase();
	}
	
	/**
	 * 将十六进制转化为十进制数字
	 * @param c
	 * @return
	 */
	private static int hex2int(char c) {
		int i = 0;
		switch (c) {
		case '1':
			i = 1;
			break;
		case '2':
			i = 2;
			break;
		case '3':
			i = 3;
			break;
		case '4':
			i = 4;
			break;
		case '5':
			i = 5;
			break;
		case '6':
			i = 6;
			break;
		case '7':
			i = 7;
			break;
		case '8':
			i = 8;
			break;
		case '9':
			i = 9;
			break;
		case 'A':
			i = 10;
			break;
		case 'B':
			i = 11;
			break;
		case 'C':
			i = 12;
			break;
		case 'D':
			i = 13;
			break;
		case 'E':
			i = 14;
			break;
		case 'F':
			i = 15;
			break;
		default:
			break;
		}
		return i;
	}

	/**
	 * 计算卡号PINBLOCK
	 * @param cardNo
	 * @return
	 */
	private static String computeCardNoPinBlock(String cardNo) {
		StringBuilder builder = new StringBuilder(cardNo);
		int pinLength = builder.length();
		builder.deleteCharAt(pinLength-1);
		pinLength = builder.length(); 
		builder.delete(0, pinLength-12);
		pinLength = builder.length();
		int leftLength = 16-pinLength;
		for (int i = 0; i < leftLength; i++) {
			builder.insert(0, '0');
		}
		return builder.toString();
	}
	
	/**
	 * 计算密码PINBLOCK
	 * @param pwd
	 * @return
	 */
	private static String computePasswordPinBlock(String pwd) {
		StringBuilder builder = new StringBuilder(pwd);
		int pinLength = builder.length();
		builder.insert(0, pinLength);
		if (String.valueOf(pinLength).length()<2) {
			builder.insert(0, 0);
		}
		pinLength = builder.length();
		int rightLength = 16-pinLength;
		for (int i = 0; i < rightLength; i++) {
			builder.append('F');
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		String cardNo = "123456789012345678";
		String pwd = "123456";
		System.out.println(computePinBlock(cardNo, pwd));
	}
	
}
