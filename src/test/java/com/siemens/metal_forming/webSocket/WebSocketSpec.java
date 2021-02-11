package com.siemens.metal_forming.webSocket;

import com.siemens.metal_forming.connection.PlcData;
import com.siemens.metal_forming.connection.opcua.PlcDataOpcua;
import com.siemens.metal_forming.service.WebSocketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class WebSocketSpec {
    @LocalServerPort
    private Integer port;
    @Autowired
    private WebSocketService webSocketService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Mock
    private PlcData plcData;
    private WebSocketStompClient webSocketStompClient;


    @BeforeEach
    public void initialize(){
        webSocketStompClient = new WebSocketStompClient(new SockJsClient(List.of(new WebSocketTransport(new StandardWebSocketClient()))));
    }

    @Test @DisplayName("test subscription to /topic/plcs/connection-status")
    void testSubscriptionToPlcConnectionEndpoint() throws Exception{
        CountDownLatch latch = new CountDownLatch(1);

        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSession stompSession = webSocketStompClient.connect("ws://localhost:" + port+"/api/ws", new StompSessionHandlerAdapter() {
        }).get(1, TimeUnit.SECONDS);

        stompSession.subscribe("/topic/plcs/connection-status", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return String.class;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object o) {
                latch.countDown();
                System.out.println("RECEIVED: "+o);
            }
        });

        //webSocketService.onConnectionStatusChange(plcData);
        for(int i=0;i<5000;i++){
            Thread.sleep(1000);
            stompSession.send("/topic/plcs/connection-status", "SENT FROM SESSION!!!");
            simpMessagingTemplate.convertAndSend("/topic/plcs/connection-status", "SENT FROM SIMPLE MESSAGING TEMPLATE!!!");
        }


        if (!latch.await(5000, TimeUnit.SECONDS)){
            fail("Message not received");
        }
    }
}
