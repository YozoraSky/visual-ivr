package com.ctbcbank.ivr.repo.gateway.encrypt;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

	public static final String PARAM_AES_ALGORITHM = "AES";
	public static final String PARAM_AES_MODE = "CBC";
	public static final String PARAM_AES_PADDING = "NoPadding";
	public static final String PARAM_AES_PROVIDER = "SunJCE";
	public static final String PARAM_AES_ENCODE = "UTF-8"; 
	
	private static String getTransformation() {
		return PARAM_AES_ALGORITHM + "/CBC/NoPadding";
	}

	private static String getProvider() {
		return "SunJCE";
	}

	public static String getMemberNoEncrypted(String plainText, String IV, String encryptionKey) throws Exception {
		
		String transformation = PARAM_AES_ALGORITHM + "/" + PARAM_AES_MODE + "/" + PARAM_AES_PADDING;
		
		SecretKey key = new SecretKeySpec(encryptionKey.getBytes(PARAM_AES_ENCODE), PARAM_AES_ALGORITHM);
		
		// encrypt bytes
		int multiple = 16;
		int plainTextMod = (plainText.getBytes(PARAM_AES_ENCODE)).length % multiple;
		if (plainTextMod != 0) {
			plainText = plainText + new String(new byte[multiple - plainTextMod]);
		}
		
		// encrypt
		byte[] encrypt = DecryptionUtils.encrypt(plainText.getBytes(PARAM_AES_ENCODE), IV, transformation,
				PARAM_AES_PROVIDER, key, PARAM_AES_ENCODE);
		return new String(Base64.getEncoder().encode(encrypt));
	}
	
	public static String getMemberNoDecrypt(String plainText, String IV, String encryptionKey) throws Exception {
		// decrypt
		byte[] decrypt = DecryptionUtils.decrypt(
				Base64.getDecoder().decode(plainText), IV, getTransformation(),
				getProvider(), getKey(encryptionKey), PARAM_AES_ENCODE);
		return new String(decrypt);
	}
	
	private static SecretKey getKey(String encryptionKey) throws Exception {
		return new SecretKeySpec(encryptionKey.getBytes(PARAM_AES_ENCODE), "AES");
	}
}