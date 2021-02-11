package com.siemens.metal_forming.service.impl;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Service @Slf4j
public class WebSocketServiceImpl implements WebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketServiceImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void onConnectionStatusChange(PlcData plcData) {
        String test = "TEST";
        simpMessagingTemplate.convertAndSend("/topic/plcs/connection-status", test);
        log.info("WS CONNECTION STATUS UPDATED");
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduled(){
        simpMessagingTemplate.convertAndSend("/topic/plcs/scheduled", "SCHEDULED");
        log.info("SCHEDULED");
    }
}
