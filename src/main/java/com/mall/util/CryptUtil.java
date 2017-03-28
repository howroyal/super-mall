package com.mall.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Encoder;

/**
 * 参照ERP加解密代码实现ERP密码加解密功能
 * @author zu.he
 *
 */
public class CryptUtil {
	
	// 密钥
	private static String keyString = "qadbA1mpYXw=";
	// 向量
	private static String ivString = "nt+VPT5cb6M=";
	
	private static byte[] keyByte = null;
	
	private static byte[] ivByte = null;
	
	static{
		keyByte = DatatypeConverter.parseBase64Binary(keyString);
		ivByte = DatatypeConverter.parseBase64Binary(ivString);
	}

	/**
	 * 解密数据
	 * @Description:
	 * @param message
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年8月23日
	 */
	public static String decrypt(String message){
		byte[] value = DatatypeConverter.parseBase64Binary(message);
		byte[] retByte = null;
		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			DESKeySpec desKeySpec = new DESKeySpec(keyByte);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(ivByte);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
			retByte = cipher.doFinal(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(retByte == null){
			return "";
		} else {
			return new String(retByte);
		}
	}

	/**
	 * 加密字符串
	 * @Description:
	 * @param message
	 * @param key
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年8月23日
	 */
	public static String encrypt(String message, String key) {
		Cipher cipher = null;
		byte[] returnByte = null;
		try{
			cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			if (cipher == null){
				return null;
			}
			DESKeySpec desKeySpec = new DESKeySpec(ivByte);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(ivByte);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			returnByte = cipher.doFinal(message.getBytes("UTF-8"));
		} catch (Exception e){
			e.printStackTrace();
		}
		return new BASE64Encoder().encode(returnByte);
	}
	
	/**
	 * 加密字符串
	 * @Description:
	 * @param message
	 * @return
	 * @Version:1.0
	 * @User:hao.wang
	 * @Date:2016年8月23日
	 */
	public static String encrypt(String message) {
		keyByte = DatatypeConverter.parseBase64Binary(keyString);
		ivByte = DatatypeConverter.parseBase64Binary(ivString);

		byte[] r = null;
		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			if(cipher == null){
				return null;
			}
			DESKeySpec desKeySpec = new DESKeySpec(keyByte);

			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(ivByte);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			r = cipher.doFinal(message.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new BASE64Encoder().encode(r);
	}

	public static byte[] convertHexString(String ss) {
		byte digest[] = new byte[ss.length() / 2];
		for (int i = 0; i < digest.length; i++) {
			String byteString = ss.substring(2 * i, 2 * i + 2);
			int byteValue = Integer.parseInt(byteString, 16);
			digest[i] = (byte) byteValue;
		}

		return digest;
	}

	public static String toHexString(byte b[]) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String plainText = Integer.toHexString(0xff & b[i]);
			if (StringUtils.isNotEmpty(plainText) && plainText.length() < 2) {
				plainText = "0" + plainText;
			}
			hexString.append(plainText);
		}

		return hexString.toString();
	}

}
