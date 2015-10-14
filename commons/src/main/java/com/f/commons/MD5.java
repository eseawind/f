package com.f.commons;

import java.security.MessageDigest;

import framework.exception.ApplicationException;

public class MD5 {
	private static final char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};     

	public static String md5Str(String s){
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            throw new ApplicationException("md5 error");
        }
	}
}
