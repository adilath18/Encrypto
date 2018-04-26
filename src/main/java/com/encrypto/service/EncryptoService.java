package com.encrypto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encrypto.dto.EncrptoDTO;
import com.encrypto.dto.RequestData;
import com.encrypto.model.EncryptoModel;
import com.encrypto.model.User;
import com.encrypto.repo.EncryptRepository;
import com.encrypto.repo.UserRepository;
import com.encrypto.utils.EncryptDataUtils;

@Service
public class EncryptoService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EncryptRepository encryptRepository;

	public void encryptData(RequestData requestData) throws Exception {

		EncrptoDTO encDTO = EncryptDataUtils.encryptData(requestData.getData());
		User sender = userRepository.findOne(requestData.getSenderId());
		User reciever = userRepository.findOne(requestData.getRecieverId());
		EncryptoModel encr = new EncryptoModel(sender, encDTO.getEncryptedData(), encDTO.getPublicKey(), reciever);
		encryptRepository.saveAndFlush(encr);
	}
	
	public List<EncryptoModel> getNotified(Integer reciever_id) {
		User reciever = userRepository.findOne(reciever_id);
		return encryptRepository.findNotRead(reciever);
	}
	
	public boolean requestKey(Long dataId) {
		List<EncryptoModel> encryptoModels = encryptRepository.findById(dataId);
		EncryptoModel encryptoModel = null;
		if(encryptoModels != null) {
			encryptoModel = encryptoModels.get(0);
			encryptoModel.setIsrequested(true);
			encryptRepository.saveAndFlush(encryptoModel);
			
			return true;
		}
		
		return false;
	}
	
	public List<EncryptoModel> requested(Integer sender_id) {
		User sender = userRepository.findOne(sender_id);
		return encryptRepository.findRequested(sender);
	}
	
	
	public boolean accept(Long dataId, boolean acceptOrReject) {
		List<EncryptoModel> encryptoModels = encryptRepository.findById(dataId);
		EncryptoModel encryptoModel = null;
		if(encryptoModels != null) {
			encryptoModel = encryptoModels.get(0);
			encryptoModel.setAccepted(acceptOrReject);
			encryptRepository.saveAndFlush(encryptoModel);
			
			return true;
		}
		
		return false;
	}
	
	public String decrypt(Long dataId) {
		byte[] encData = null;
		List<EncryptoModel> encryptoModels = encryptRepository.findById(dataId);
		EncryptoModel encryptoModel = null;
		if(encryptoModels != null) {
			encryptoModel = encryptoModels.get(0);
			encData = EncryptDataUtils.decryptData(encryptoModel.getData(), encryptoModel.getPublicKey());
		}
		
		return new String(encData);
	}
	

}
