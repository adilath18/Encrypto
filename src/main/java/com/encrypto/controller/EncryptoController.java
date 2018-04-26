package com.encrypto.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.encrypto.dto.RequestData;
import com.encrypto.model.EncryptoModel;
import com.encrypto.model.User;
import com.encrypto.repo.UserRepository;
import com.encrypto.service.EncryptoService;

@Controller
public class EncryptoController {

	@Autowired
	private EncryptoService encryptoService;

	@Autowired
	private UserRepository userRepository;

	
	
	@RequestMapping(value = "/users")
	@ResponseBody
	public List<User> getUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return userRepository.findAll();
	}

	
	@RequestMapping(value = "/encryptData")
	@ResponseBody
	public boolean encrypto(@RequestBody RequestData requestData, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			encryptoService.encryptData(requestData);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	
	@RequestMapping(value = "/checkData")
	@ResponseBody
	public List<EncryptoModel> checkData(@RequestBody RequestData requestData, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return encryptoService.getNotified(requestData.getRecieverId());
		
	}
	
	@RequestMapping(value = "/requestKey")
	@ResponseBody
	public boolean requestkey(@RequestBody RequestData requestData, HttpServletRequest request) {
		return encryptoService.requestKey(requestData.getDataId());
	}
	
	
	@RequestMapping(value = "/requested")
	@ResponseBody
	public List<EncryptoModel> requestedList(@RequestBody RequestData requestData, HttpServletRequest request) {
		return encryptoService.requested(requestData.getSenderId());
	}

	@RequestMapping(value = "/accept")
	@ResponseBody
	public boolean acceptOrReject(@RequestBody RequestData requestData, HttpServletRequest request) {
		return encryptoService.accept(requestData.getDataId(), requestData.isAccept());
	}
	
	@RequestMapping(value = "/decrypt")
	@ResponseBody
	public String decrypt(@RequestBody RequestData requestData, HttpServletRequest request) {
		return encryptoService.decrypt(requestData.getDataId());
	}
	
}
