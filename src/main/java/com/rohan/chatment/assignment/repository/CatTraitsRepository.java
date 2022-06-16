package com.rohan.chatment.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.chatment.assignment.domain.CatTraits;


public interface CatTraitsRepository extends JpaRepository<CatTraits,Integer> {

	public CatTraits findFirstByOrderByFactAsc(); 
}
