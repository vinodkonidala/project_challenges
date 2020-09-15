package com.project.challenge.app.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.challenge.app.entity.Enrollee;

@Repository
@Transactional
public class UserRepository {

	@Autowired
	private EntityManager entityManager;
	
	public Enrollee getEnrollee(Integer enrolleId){
		
		return entityManager.find(Enrollee.class, enrolleId);
	}
	
	public Enrollee saveEnrollee(Enrollee enrollee){
		
		entityManager.persist(enrollee);
		
		return enrollee;
	}
	
	
	public List<Enrollee> getAllEnrollees(){
		
		TypedQuery<Enrollee> typedQuery = entityManager.createNamedQuery("getAllEnrollees", Enrollee.class);

		List<Enrollee> enrollees = typedQuery.getResultList();
		
		return enrollees;
		
	}
		
	public Enrollee updateEnrolleeInfo(Map<String, Object> params){
		
		Integer enrolleeId = (Integer) params.get("ID");
		
		Enrollee enrollee = entityManager.find(Enrollee.class, enrolleeId);

		if(params.containsKey("NAME")) {
			String name = params.get("NAME").toString();
			enrollee.setName(name);
		}
		
		if(params.containsKey("ACTIVATION_STATUS")) {
			String activationStatus = params.get("ACTIVATION_STATUS").toString();
			enrollee.setActivationStatus(activationStatus);
		}
		
		entityManager.merge(enrollee);
		
		return enrollee;
		
	}
	
}
