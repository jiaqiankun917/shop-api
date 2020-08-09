package com.fh.shop.api.util;


import java.security.MessageDigest;

public class MD5 {

	public final static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	// 测试
	public static void main(String[] args) {
		System.out.println(MD5.md5("123"));
	}


	public static String sign(String info , String secret){
		return md5(info+"===="+secret);
	}

	public static final String SECRET = "LSHDSAJNkdusn#@!%$#^#!FDSA$#$^^*%1";

}
