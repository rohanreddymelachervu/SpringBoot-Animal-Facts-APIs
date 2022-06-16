package com.rohan.chatment.assignment.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="access_log", schema="animal_traits_db")
public class AccessLog {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false, length=20)
	private String ipAddress;
	@Column(nullable=false, length=20)
	private String animalType;
	@Column(nullable=false, length=255)
	private String factReturned;
	@Column(nullable=false, length=20)
	private LocalDateTime localDateTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIP_Address() {
		return ipAddress;
	}
	public void setIP_Address(String iP_Address) {
		ipAddress = iP_Address;
	}
	public String getAnimalType() {
		return animalType;
	}
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	public String getFactReturned() {
		return factReturned;
	}
	public void setFactReturned(String factReturned) {
		this.factReturned = factReturned;
	}
	public LocalDateTime getTimeStamp() {
		return localDateTime;
	}
	public void setTimeStamp(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	} 
}
