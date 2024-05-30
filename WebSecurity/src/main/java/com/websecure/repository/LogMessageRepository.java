package com.websecure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.websecure.entity.LogMessage;

public interface LogMessageRepository extends JpaRepository<LogMessage, Long>{


}
