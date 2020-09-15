package com.project.challenge.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENROLLEE")
@NamedQuery(name = "getAllEnrollees", query = "select e from Enrollee e")
public class Enrollee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EN_ID")
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ACTIVATION_STATUS")
	@JsonProperty("activation-status")
	private String activationStatus;
	
	@Column(name = "DOB")
	private String dob;
}
