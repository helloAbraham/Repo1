package com.websecure.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websecure.entity.LogMessage;
import com.websecure.repository.LogMessageRepository;

@Service
public class LogService {

    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    private LogMessageRepository logMessageRepository;

    public void log(String message) {
        logger.info(message);
        LogMessage logMessage = new LogMessage();
        logMessage.setTimestamp(LocalDateTime.now());
        logMessage.setLevel("INFO");
        logMessage.setLogger(LogService.class.getName());
        logMessage.setMessage(message);
        logMessageRepository.save(logMessage);
    }
}

