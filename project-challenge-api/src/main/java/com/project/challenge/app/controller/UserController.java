package com.project.challenge.app.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.challenge.app.entity.Enrollee;
import com.project.challenge.app.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/get-enrollee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Enrollee>> getEnrollee(@RequestParam("enrollee-id") Integer enrolleeId ){
		
		List<Enrollee> enrollees = userService.getAllEnrollees();
		
		
		return new ResponseEntity<List<Enrollee>>(enrollees, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save-enrollee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Enrollee> getEnrollee(@RequestBody Enrollee enrollee){
		
		enrollee = userService.saveEnrollee(enrollee);
		
		return new ResponseEntity<Enrollee>(enrollee, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-enrollees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Enrollee>> getEnrollees(){
		
		List<Enrollee> enrollees = userService.getAllEnrollees();
		
		
		return new ResponseEntity<List<Enrollee>>(enrollees, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/update-enrollee/{enrollee_id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> updateEnrolleeInfo(@PathVariable("enrollee_id") Integer enrolleeId,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "activation-status", required = false) Boolean activationStatus){
		
		Map<String, Object> responseMap = new LinkedHashMap<String, Object>();
		
		if(enrolleeId == null && activationStatus == null) {
			
			responseMap.put("error", "The input details are not valid to update the enrollee");
		}else {
			
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("ID", enrolleeId);
			
			if(name != null) {
				paramsMap.put("NAME", name);
			}
			
			if(activationStatus != null) {
				paramsMap.put("ACTIVATION_STATUS", activationStatus);
			}
			
			Enrollee enrollee = userService.updateEnrolleeInfo(paramsMap);
			
			responseMap.put("success", "details are updated successfully!");
			responseMap.put("updated-enrollee", enrollee);
		}
		
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
}
