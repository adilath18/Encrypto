package com.encrypto.dto;

public class EncrptoDTO {

	private byte[] encryptedData;
	private byte[] publicKey;
	private byte[] privateKey;

	public EncrptoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EncrptoDTO(byte[] encryptedData, byte[] publicKey, byte[] privateKey) {
		super();
		this.encryptedData = encryptedData;
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}

	public byte[] getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(byte[] encryptedData) {
		this.encryptedData = encryptedData;
	}

	public byte[] getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(byte[] publicKey) {
		this.publicKey = publicKey;
	}

	public byte[] getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(byte[] privateKey) {
		this.privateKey = privateKey;
	}

}
