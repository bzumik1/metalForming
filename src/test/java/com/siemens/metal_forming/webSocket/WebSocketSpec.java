package com.siemens.metal_forming.webSocket;

import com.siemens.metal_forming.dto.PlcDto;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.fail;
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@Disabled("Disabled because we don't use SockJS")
public class WebSocketSpec {
    @LocalServerPort
    private Integer port;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    private WebSocketStompClient webSocketStompClient;


    @BeforeEach
    public void initialize(){
        webSocketStompClient = new WebSocketStompClient(new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient()))));
    }


    @Test @DisplayName("test of connection")
    void testOfConnection() throws Exception{
        webSocketStompClient.setMessageConverter(new StringMessageConverter());
        CountDownLatch latch = new CountDownLatch(1);

        StompSession stompSession = webSocketStompClient.connect("ws://localhost:" + port+"/api/ws", new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders headers) {
                log.info("Client connected: headers {}", headers);
                StompSession.Subscription subscription = session.subscribe("/topic/test", this);
            }
            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                latch.countDown();
                log.info("Client received: payload {}, headers {}", payload, headers);
            }
            @Override
            public void handleException(StompSession session, StompCommand command,
                                        StompHeaders headers, byte[] payload, Throwable exception) {
                log.error("Client error: exception {}, command {}, payload {}, headers {}",
                        exception.getMessage(), command, payload, headers);
            }
            @Override
            public void handleTransportError(StompSession session, Throwable exception) {
                log.error("Client transport error: error {}", exception.getMessage());
            }
        }).get(1, TimeUnit.SECONDS);


        Thread.sleep(100);
        log.info("Server sending message: TEST");
        simpMessagingTemplate.convertAndSend("/topic/test", "TEST");

        if (!latch.await(2, TimeUnit.SECONDS)){
            fail("Message not received");
        }
    }

    @Test @DisplayName("sending JSON correctly")
    void sendingJsonCorrectly() throws Exception{
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
        CountDownLatch latch = new CountDownLatch(1);

        StompSession stompSession = webSocketStompClient.connect("ws://localhost:" + port+"/api/ws", new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders headers) {
                log.info("Client connected: headers {}", headers);
                StompSession.Subscription subscription = session.subscribe("/topic/test", this);
            }
            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                latch.countDown();
                log.info("Client received: payload {}, headers {}", payload, headers);
            }
            @Override
            public void handleException(StompSession session, StompCommand command,
                                        StompHeaders headers, byte[] payload, Throwable exception) {
                log.error("Client error: exception {}, command {}, payload {}, headers {}",
                        exception.getMessage(), command, payload, headers);
                log.debug("Payload as JSON: {}", new String(payload));
            }

            @Override
            public Type getPayloadType(StompHeaders headers) {
                log.info("Client payload type: {}", super.getPayloadType(headers));
                return PlcDto.Response.Connection.class;
            }

            @Override
            public void handleTransportError(StompSession session, Throwable exception) {
                log.error("Client transport error: error {}", exception.getMessage());
            }
        }).get(1, TimeUnit.SECONDS);


        Thread.sleep(100);
        PlcDto.Response.Connection plcDtoConnection = PlcDto.Response.Connection.builder().id(1L).connectionStatus(ConnectionStatus.CONNECTED).build();
        log.info("Server sending message: {}", plcDtoConnection);
        simpMessagingTemplate.convertAndSend("/topic/test", plcDtoConnection);

        if (!latch.await(2, TimeUnit.SECONDS)){
            fail("Message not received");
        }
    }
}
