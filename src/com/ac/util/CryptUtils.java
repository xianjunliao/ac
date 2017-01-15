package com.ac.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptUtils {
	private static Key key;
	private static String KEY_STR = "mykey";
	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(KEY_STR.getBytes());
			generator.init(secureRandom);
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 瀵瑰瓧绗︿覆杩涜鍔犲瘑锛岃繑鍥濨ASE64鐨勫姞瀵嗗瓧绗︿覆 <鍔熻兘璇︾粏鎻忚堪>
	 * 
	 * @param str
	 * @return
	 * @see [绫汇�绫�鏂规硶銆佺被#鎴愬憳]
	 */
	public static String getEncryptString(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		BASE64Encoder base64Encoder = new BASE64Encoder();

		try {
			byte[] strBytes = str.getBytes("UTF-8");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return base64Encoder.encode(encryptStrBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 瀵笲ASE64鍔犲瘑瀛楃涓茶繘琛岃В瀵�<鍔熻兘璇︾粏鎻忚堪>
	 * 
	 * @param str
	 * @return
	 * @see [绫汇�绫�鏂规硶銆佺被#鎴愬憳]
	 */
	public static String getDecryptString(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		BASE64Decoder base64Decoder = new BASE64Decoder();
		try {
			byte[] strBytes = base64Decoder.decodeBuffer(str);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return new String(encryptStrBytes, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) {
		// jdbc:oracle:thin:@127.0.0.1:1521:ORCL
		// jdbc:oracle:thin:@192.168.59.138:1521:ORCL
		// jdbc:oracle:thin:@172.16.34.146:1521:prod1 tboss tboss
		// jdbc:oracle:thin:@172.16.34.151:1521:tbosspro1 tboss Tboss2016-db
		// +1P7T9qt4FfNJRTPWsgTXzoyidXN6xvS57j8ienzh/Gs7kp+Ohd9U4cRkw0giuSU
		// ZIW3D9GBNIU= XN9xCWXs8inlmgHv4O/BDg==
		// admin 瀵嗙爜 Adm_lltf*0 8d2w0dba3Oucnk6lMsfvOg==
//		System.out.println("[" + getEncryptString("jdbc:oracle:thin:@192.168.186.129:1521:orcl") + "]");
//		// System.out.println(getEncryptString("Adm_lltf*0"));
//		System.out.println(getEncryptString("Tboss_lltf0829"));
		System.out.println(getDecryptString("UvJNDLqTSYw="));
	}
 
}
