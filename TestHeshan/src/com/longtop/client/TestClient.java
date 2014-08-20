
//
//  TestClient.h
//  AES Test Java
//
//  Created by kaitiren on 12-8-27.
//  Copyright (c) 2012年 kaitiren. All rights reserved.
//

package com.longtop.client;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import com.longtop.client.codec.encryp.EncrypAES;
import com.longtop.client.codec.encryp.EncrypMD5;
import com.longtop.client.codec.encryp.HexTransfer;

/**
 * 在原报文协议基础上增加一层加密协议
 * 
 * @author Administrator
 *
 */
public class TestClient {

	/**
	 * 对报文原文采用AES密钥加密后发送
	 * @param message 发送到广发行的请求报文原文
	 */
	public static String encrypMessage(String message){
		EncrypAES encryp = EncrypAES.getInstance();
		String outputStr = null;
		try {
			//对原文加密
			outputStr = HexTransfer.byteArr2HexStr(encryp.Encrytor(message));
			//对加密后的密文计算md5码
			String md5_ = HexTransfer.byteArr2HexStr(EncrypMD5.encrypt(outputStr));
			//组装新报文为“32位定长md5+变长密文”
			outputStr = md5_+outputStr;
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		int len = outputStr.getBytes().length;
		String len_=new String(len+"");
		for(int i=len_.length();i<6;i++){
			len_ = "0"+len_;
		}
		//组装新报文为“6位长度+32位定长md5+变长密文”
		return len_+outputStr;
	}
	/**
	 * 对报文密文采用AES密钥解密后再做业务操作
	 * @param message 广发行返回的报文密文
	 * @throws NoSuchAlgorithmException 
	 */
	public static String descrypMessage(String message){
		//前6位为长度，socket协议可能用到
		//读取32位MD5摘要
		String md5 = message.substring(6,38);
		//一次性全部取出密文然后解密
		String content = message.substring(38);
		String md5_ = null;//客户端计算的md5
		String content_ = null;//客户端解密后的明文
		try {
			//重新计算md5码
			md5_ = HexTransfer.byteArr2HexStr(EncrypMD5.encrypt(content));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		if(!md5.equals(md5_)){
			//MD5摘要不一致，报文被篡改，出错处理
		}
		EncrypAES encryp = EncrypAES.getInstance();
		try {
			//解密
			content_ = new String(encryp.Decryptor(HexTransfer.hexStr2ByteArr(content)));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return content_;
	}
	
	public static void main(String[] args){
		//Socket报文采用6位长度值+md5+密文
		
		//举例原Socket报文"000109"+"0000"+报文查询内容体
		//String msg = "0001090000201206276282600038881051   138888000001000.00272179001032270039123456789012345678901234567890123456789012";
		//String msg_ = encrypMessage(msg);
		//String msg__ = descrypMessage(msg_);
		//System.out.println("原文："+msg);
		//System.out.println("密文："+msg_);
		//System.out.println("解密："+msg__);
		
		//FTP采用32位MD5+密文形式存储
		
		//读取解释FTP文件时步骤：
		//1、截取前32位+32位后密文
		//2、计算32位后密文的md5，是否和前32位md5一致
		//3、如果一致，对32位后密文解密成明文
		//4、对明文走原来业务流程
		
		//存储FTP文件时步骤：
		//1、走原业务流程生成FTP内容体
		//2、对FTP内容体加密成密文
		//3、对密文计算32位md5码
		//4、把32位md5码+密文存储为FTP文件
		
		EncrypAES encryp = EncrypAES.getInstance();
		String outputStr = null;
			//对原文加密
		try {
			outputStr = HexTransfer.byteArr2HexStr(encryp.Encrytor("360TOP1奢侈品"));
			System.out.println("after encode:"+outputStr);
			outputStr= new String(encryp.Decryptor(HexTransfer.hexStr2ByteArr("fa018bf93d63f0716a4e2419fefa9882a66e502e8f9a5e6e0531de3d933ae4de")));
			System.out.println("after decode:"+outputStr);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
