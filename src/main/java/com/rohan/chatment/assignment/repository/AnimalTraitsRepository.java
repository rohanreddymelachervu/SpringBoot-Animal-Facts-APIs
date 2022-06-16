package com.rohan.chatment.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.chatment.assignment.domain.AnimalTraits;

public interface AnimalTraitsRepository extends JpaRepository<AnimalTraits,Integer> {
	
}
