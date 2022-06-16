package com.rohan.chatment.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.chatment.assignment.domain.DogTraits;


public interface DogTraitsRepository extends JpaRepository<DogTraits,Integer> {
	public DogTraits findFirstByOrderByFactAsc(); 
}
