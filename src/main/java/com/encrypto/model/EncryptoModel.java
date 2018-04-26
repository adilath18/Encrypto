package com.encrypto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "encrypt_data")
public class EncryptoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "data_id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "sender_id", nullable = false)
	private User sender;
	
	@Column(name = "data", nullable = false)
	private byte[] data;
	
	@Column(name = "public_key", nullable = false)
	@JsonIgnore
	private byte[] publicKey;

	@Column(name="is_Read", nullable = false)
	private boolean isRead = false;
	
	@Column(name = "is_accepted", nullable = false)
	private boolean isAccepted = false;
	
	@ManyToOne
	@JoinColumn(name="reciever_id", nullable = false)
	private User reciever;
	
	@Column(name = "requested")
	private boolean isrequested = false;
	
	public EncryptoModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EncryptoModel(User sender, byte[] data, byte[] publicKey, User reciever) {
		super();
		this.sender = sender;
		this.data = data;
		this.publicKey = publicKey;
		this.reciever = reciever;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public byte[] getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(byte[] publicKey) {
		this.publicKey = publicKey;
	}

	public User getReciever_id() {
		return reciever;
	}

	public void setReciever_id(User reciever) {
		this.reciever = reciever;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}

	public boolean isIsrequested() {
		return isrequested;
	}

	public void setIsrequested(boolean isrequested) {
		this.isrequested = isrequested;
	}
	
	
	
}
