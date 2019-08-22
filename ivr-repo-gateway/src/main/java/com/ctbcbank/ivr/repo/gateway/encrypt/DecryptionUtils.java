package com.ctbcbank.ivr.repo.gateway.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public abstract class DecryptionUtils {

	/**
	 * encrypt
	 * @param plainText
	 * @param IV
	 * @param encryptionKey
	 * @param transformation
	 * @param provider
	 * @param key
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] plainText, String IV, String transformation, String provider, SecretKey key, String encode) throws Exception {
		return decryptionImp(Cipher.ENCRYPT_MODE, plainText, IV, transformation, provider, key, encode);
	}
	
	/**
	 * decrypt
	 * @param plainText
	 * @param IV
	 * @param encryptionKey
	 * @param transformation
	 * @param provider
	 * @param key
	 * @param encode
	 * @return
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] plainText, String IV, String transformation, String provider, SecretKey key, String encode) throws Exception {
		return decryptionImp(Cipher.DECRYPT_MODE, plainText, IV, transformation, provider, key, encode);
	}
	
	/**
	 * decryption implemant
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	private static byte[] decryptionImp(int mode, byte[] plainText, String IV, String transformation, String provider, SecretKey key, String encode) throws Exception {
		Cipher cipher = Cipher.getInstance(transformation, provider);
		cipher.init(mode, key, new IvParameterSpec(IV.getBytes(encode)));
		return cipher.doFinal(plainText);
	}
	
}
