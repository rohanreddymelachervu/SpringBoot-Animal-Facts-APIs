package com.rohan.chatment.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.chatment.assignment.domain.AccessLog;

public interface AccessLogRepository extends JpaRepository<AccessLog,Integer> {

}
