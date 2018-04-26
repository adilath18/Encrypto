package com.encrypto.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.encrypto.dto.EncrptoDTO;

public class EncryptDataUtils {
	
	private static final String xform = "RSA/ECB/PKCS1PADDING";

	private static byte[] encrypt(byte[] inpBytes, PrivateKey key, String xform) throws Exception {
		Cipher cipher = Cipher.getInstance(xform);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(inpBytes);
	}

	private static byte[] decrypt(byte[] inpBytes, PublicKey key, String xform) throws Exception {
		Cipher cipher = Cipher.getInstance(xform);
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(inpBytes);
	}

	public static EncrptoDTO encryptData(String data){
		byte[] encBytes = null;
		EncrptoDTO enc = null;
		try {
		// Generate a key-pair
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(512); // 512 is the keysize.
		KeyPair kp = kpg.generateKeyPair();
		PublicKey pubk = kp.getPublic();
		PrivateKey prvk = kp.getPrivate();
		System.out.println(pubk.toString());

		byte[] dataBytes = data.getBytes();

		encBytes = encrypt(dataBytes, prvk, xform);
		
		enc = new EncrptoDTO(encBytes, pubk.getEncoded(), prvk.getEncoded());
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return enc;
	} 
	
	public static byte[] decryptData(byte[] encBytes, byte[] publicKey) {
		
		byte[] decBytes = null;
		try {
			PublicKey pubKey = KeyFactory.getInstance("RSA")
	                .generatePublic(new X509EncodedKeySpec(publicKey));
			decBytes = decrypt(encBytes, pubKey, xform);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return decBytes;
	}
	
}
