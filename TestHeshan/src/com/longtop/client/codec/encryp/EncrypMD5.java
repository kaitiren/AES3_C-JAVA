
//
//  EncrypMD5.java
//  MD5 JAVA
//
//  Created by kaitiren on 12-8-27.
//  Copyright (c) 2012 kaitiren. All rights reserved.
//

package com.longtop.client.codec.encryp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypMD5 {
	
	public static byte[] encrypt(String info) throws NoSuchAlgorithmException{
		byte[] srcBytes = info.getBytes();
		return encrypt(srcBytes);
	}
	
	public static byte[] encrypt(byte[] info) throws NoSuchAlgorithmException{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		//使用srcBytes更新摘要
		md5.update(info);
		//完成哈希计算，得到result
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}
	
	
	public static void main(String args[]) throws NoSuchAlgorithmException{
		String msg = "郭德纲-精品相声技术";
		byte[] resultBytes = EncrypMD5.encrypt(msg);
		
		System.out.println("密文是：" + new String(resultBytes));
		System.out.println("明文是：" + msg);
	}

}
