package com.siemens.metal_forming.entity;

import com.siemens.metal_forming.enumerated.ConnectionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.sql.Timestamp;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("<= CONNECTION SPECIFICATION =>")
public class ConnectionSpec {
    Validator validator;
    Connection connection;
    @BeforeEach
    void initialize(){
        connection = new Connection();
    }

    @Nested @DisplayName("NEW CONNECTION")
    class newConnection{
        @Test @DisplayName("is created with current timestamp")
        void isCreatedWithCurrentTimestamp(){
            long acceptedTimeDifferenceInMillis = 1000;

            long connectionMillis = connection.getLastStatusChange().getTime();
            long currentMillis = System.currentTimeMillis();

            assertThat(Math.abs((connectionMillis-currentMillis))).isLessThan(acceptedTimeDifferenceInMillis);
        }

        @Test @DisplayName("is created with status DISCONNECTED")
        void isCreatedWithStatusDisconnected(){
            assertThat(connection.getStatus()).isEqualTo(ConnectionStatus.DISCONNECTED);
        }
    }




    @Test @DisplayName("method updateTimestamp set timestamp to current time")
    void updateTimeSetsTimestampToCurrentTime(){
        connection.updateTimestamp();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //needs to be first line
        assertThat(connection.getLastStatusChange()).isEqualTo(timestamp);
    }

    @Test @DisplayName("method setStatus set status and last lastStatusUpdate to current time")
    void setStatusMethodChangeLastStatusUpdate(){
        connection.getLastStatusChange().setTime(0);
        connection.setStatus(ConnectionStatus.CONNECTED);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); //needs to be first line

        assertThat(connection.getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
        assertThat(connection.getLastStatusChange()).isEqualTo(timestamp);
    }

    @Test @DisplayName("constructor with status attribute set status")
    void constructorSetsStatus(){
        connection = new Connection(ConnectionStatus.CONNECTED);
        assertThat(connection.getStatus()).isEqualTo(ConnectionStatus.CONNECTED);
    }

    @Nested @DisplayName("VALIDATION")
    class validation{
        @BeforeEach
        void initializeForValidation(){
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }

        @Test @DisplayName("is invalid when status is null")
        void isInvalidWhenStatusIsNull(){
            connection.setStatus(null);
            Set<ConstraintViolation<Connection>> violations = validator.validate(connection);
            assertThat(violations
                    .stream()
                    .filter(plcConstraintViolation -> plcConstraintViolation.getPropertyPath().toString().equals("status"))
                    .findFirst()).isNotEmpty();
        }
    }
}
