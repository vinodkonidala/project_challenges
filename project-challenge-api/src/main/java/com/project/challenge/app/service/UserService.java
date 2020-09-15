package com.project.challenge.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.challenge.app.entity.Enrollee;
import com.project.challenge.app.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Enrollee saveEnrollee(Enrollee enrollee){
		
		return userRepository.saveEnrollee(enrollee);
	}
	
	public Enrollee updateEnrolleeInfo(Map<String, Object> params){
		
		return userRepository.updateEnrolleeInfo(params);
	}
	
	public List<Enrollee> getAllEnrollees(){
		
		return userRepository.getAllEnrollees();
	}
	
	public Enrollee getAllEnrollee(Integer enrolleeId){
		
		return userRepository.getEnrollee(enrolleeId);
	}
}
