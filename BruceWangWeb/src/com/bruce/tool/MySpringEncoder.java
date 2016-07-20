package com.bruce.tool;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import sun.misc.BASE64Encoder;

//自定义编解码方式（解码未用到），在进行用户认证的时候，使用该encoder，代替spring自己的encoder。
@SuppressWarnings("deprecation")
public class MySpringEncoder implements PasswordEncoder{ 
	/**
	* DES算法密钥
	*/
	private static final byte[] DES_KEY = { 66, 127, 56, 33, -32, -55, -128, -65 };
	
	@Override
	public String encodePassword(String rawPass, Object salt) throws DataAccessException {
		// TODO Auto-generated method stub
			   String pass = null;
			   try {
			    pass = encryptBasedDes(rawPass,salt);
			   } catch (Exception e) {
			    e.printStackTrace();
			   }
			   return pass;
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		// TODO Auto-generated method stub
		  String pass1 = "" + encPass;
		  String pass2 = encodePassword(rawPass, salt);
		  return pass1.equals(pass2);
	}
	
	
	
	/**
	* 数据加密，算法（DES）
	* 
	* @param data
	*            要进行加密的数据
	* @return 加密后的数据
	*/
	public static String encryptBasedDes(String data,Object salt) {
		String encryptedData = null;
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 加密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			// 加密，并把字节数组编码成字符串
			if (salt == null) {
				encryptedData = new BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
			}else {
				data = data + salt.toString();
				encryptedData = new BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
			}
			encryptedData = byte2hex(encryptedData.getBytes());
		} catch (Exception e) {
			// log.error("加密错误，错误信息：", e);
			throw new RuntimeException("加密错误，错误信息：", e);
		}
	
	return encryptedData;
	}
	
	
	/**
	* 数据解密，算法（DES）
	* 
	* @param cryptData
	*            加密数据
	* @return 解密后的数据
	*/
	public static String decryptBasedDes(String cryptData) {
		String decryptedData = null;
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 解密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			// 把字符串解码为字节数组，并解密
			decryptedData = new String(hex2byte(cryptData));
			decryptedData = new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(decryptedData)));
		} catch (Exception e) {
			// log.error("解密错误，错误信息：", e);
			throw new RuntimeException("解密错误，错误信息：", e);
		}
		
		return decryptedData;
	}
	
	/**
	* 二进制转字符串
	* @param b
	* @return
	*/
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1){
				hs = hs + "0" + stmp;
			}else{
				hs = hs + stmp;
			}
		}
		return hs;
	}
	
	/**
	* 字符串转二进制
	* @param str
	* @return
	*/
	public static byte[] hex2byte(String str) { 
		if (str == null) return null;
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1) return null;
		byte[] b = new byte[len / 2];
		try {
		for (int i = 0; i < str.length(); i += 2) {
			b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
		}
			return b;
		} catch (Exception e) {
			return null;
		}
	}

      
}