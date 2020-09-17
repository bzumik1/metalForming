package com.siemens.metal_forming.testBuilders.specification;

import com.siemens.metal_forming.entity.*;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import com.siemens.metal_forming.testBuilders.TestConnectionBuilder;
import org.junit.jupiter.api.*;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

public class TestConnectionBuilderSpec extends TestBuilderSpec{
    TestConnectionBuilder testConnectionBuilder;

    TestConnectionBuilderSpec() {
        super(Connection.class, TestConnectionBuilder.class);
    }

    @BeforeEach
    void initialize(){
        testConnectionBuilder = new TestConnectionBuilder();
    }

    @Nested
    @DisplayName("SPECIAL METHODS")
    class SpecialMethods{

    }


    @Nested @DisplayName("DIRECT SETTING METHODS")
    class DirectSettingMethods{
        @Test @DisplayName("sets id of new connection")
        void setsIdOfNewConnection(){
            Connection testConnection = testConnectionBuilder.id(99L).build();

            assertThat(testConnection.getId()).isEqualTo(99L);
        }

        @Test @DisplayName("sets lastStatusChange of new connection")
        void setsLastStatusUpdateOfNewConnection(){
            Connection testConnection = testConnectionBuilder.lastStatusChange(new Timestamp(1L)).build();

            assertThat(testConnection.getLastStatusChange()).isEqualTo(new Timestamp(1L));
        }

        @Test @DisplayName("sets status of new connection")
        void setsStatusNewConnection(){
            Connection testConnection = testConnectionBuilder.status(ConnectionStatus.CONNECTED).build();

            assertThat(testConnection.getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
        }
    }
}
