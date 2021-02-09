package com.siemens.metal_forming;

import com.siemens.metal_forming.connection.PlcConnector;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.repository.PlcRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@DisplayName("<= APPLICATION STARTUP MANAGER SPECIFICATION =>")
@ExtendWith(MockitoExtension.class)
public class ApplicationStartupManagerSpec {
    private ApplicationStartupManager applicationStartupManager;
    @Mock ContextRefreshedEvent contextRefreshedEvent;
    @Mock PlcRepository plcRepository;
    @Mock PlcConnector plcConnector;

    @BeforeEach
    void initialize(){
        applicationStartupManager = new ApplicationStartupManager(plcRepository, plcConnector);
    }

    @Nested
    @DisplayName("CONNECT ALL PLCS IN DATABASE")
    class ConnectAllPlcsInDatabase{
        @Test
        @DisplayName("For all plcs in database triggers connectPlc method")
        void forAllPlcsInDatabaseTriggersConnectPlc(){
            List<Plc> plcsInDatabase = List.of(Plc.builder().name("first plc").build(), Plc.builder().name("second plc").build());

            when(plcRepository.findAll()).thenReturn(plcsInDatabase);
            when(plcConnector.connect(any())).thenAnswer(returnsFirstArg());

            applicationStartupManager.onApplicationStartup(contextRefreshedEvent);

            verify(plcConnector,times(2)).connect(any());
        }
    }
}
