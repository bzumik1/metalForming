package com.siemens.metal_forming.connection;

import com.siemens.metal_forming.connection.observer.*;
import com.siemens.metal_forming.enumerated.StopReactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("<= PLC DATA SPECIFICATION =>")
public class PlcDataSpec {
    PlcData plcData;

    @BeforeEach
    void initialize(){
        plcData = new PlcData("192.168.0.1") {
            @Override
            public void notifyPlcAboutCollision(StopReactionType stopReaction) {

            }

            @Override
            public void disconnect() {

            }
        };
    }

    @Test @DisplayName("removes all observes")
    void removesAllObservers(){
        SerialNumberObserver serialNumberObserver = mock(SerialNumberObserver.class);
        FirmwareNumberObserver firmwareNumberObserver = mock(FirmwareNumberObserver.class);
        ToolDataObserver toolDataObserver = mock(ToolDataObserver.class);
        MeasuredCurveObserver measuredCurveObserver = mock(MeasuredCurveObserver.class);
        MotorCurveObserver motorCurveObserver = mock(MotorCurveObserver.class);
        ConnectionStatusObserver connectionStatusObserver = mock(ConnectionStatusObserver.class);

        plcData.registerSerialNumberObserver(serialNumberObserver);
        plcData.registerFirmwareNumberObserver(firmwareNumberObserver);
        plcData.registerToolDataObserver(toolDataObserver);
        plcData.registerMeasuredCurveObserver(measuredCurveObserver);
        plcData.registerMotorCurveObserver(motorCurveObserver);
        plcData.registerConnectionStatusObserver(connectionStatusObserver);

        plcData.removeAllObservers();

        plcData.notifySerialNumberObservers();
        plcData.notifyFirmwareNumberObservers();
        plcData.notifyToolDataObservers();
        plcData.notifyMeasuredCurveObservers();
        plcData.notifyMotorCurveObservers();
        plcData.notifyConnectionStatusObservers();

        verify(serialNumberObserver, never().description("serialNumberObserver wasn't removed")).onSerialNumberChange(plcData);
        verify(firmwareNumberObserver, never().description("firmwareNumberObserver wasn't removed")).onFirmwareNumberChange(plcData);
        verify(toolDataObserver, never().description("toolDataObserver wasn't removed")).onToolDataChange(plcData);
        verify(measuredCurveObserver, never().description("measuredCurveObserver wasn't removed")).onMeasuredCurveChange(plcData);
        verify(motorCurveObserver, never().description("motorCurveObserver wasn't removed")).onMotorCurveChange(plcData);
        verify(connectionStatusObserver, never().description("connectionStatusObserver wasn't removed")).onConnectionStatusChange(plcData);
    }

    @Nested @DisplayName("OBSERVER PATTERN")
    class ObserverPattern{
        @Nested @DisplayName("SERIAL NUMBER SOURCE")
        class SerialNumberSource{
            @Test @DisplayName("adds observer")
            void addsObserver(){
                SerialNumberObserver serialNumberObserver = Mockito.mock(SerialNumberObserver.class);

                plcData.registerSerialNumberObserver(serialNumberObserver);
                plcData.notifySerialNumberObservers();

                verify(serialNumberObserver, times(1)).onSerialNumberChange(plcData);
            }

            @Test @DisplayName("removes observer")
            void removesObserver(){
                SerialNumberObserver serialNumberObserver = Mockito.mock(SerialNumberObserver.class);

                plcData.registerSerialNumberObserver(serialNumberObserver);
                plcData.removeSerialNumberObserver(serialNumberObserver);
                plcData.notifySerialNumberObservers();

                verify(serialNumberObserver, never()).onSerialNumberChange(plcData);
            }
        }

        @Nested @DisplayName("FIRMWARE NUMBER SOURCE")
        class FirmwareNumberSource{
            @Test @DisplayName("adds observer")
            void addsObserver(){
                FirmwareNumberObserver firmwareNumberObserver = Mockito.mock(FirmwareNumberObserver.class);

                plcData.registerFirmwareNumberObserver(firmwareNumberObserver);
                plcData.notifyFirmwareNumberObservers();

                verify(firmwareNumberObserver, times(1)).onFirmwareNumberChange(plcData);
            }

            @Test @DisplayName("removes observer")
            void removesObserver(){
                FirmwareNumberObserver firmwareNumberObserver = Mockito.mock(FirmwareNumberObserver.class);

                plcData.registerFirmwareNumberObserver(firmwareNumberObserver);
                plcData.removeFirmwareNumberObserver(firmwareNumberObserver);
                plcData.notifyFirmwareNumberObservers();

                verify(firmwareNumberObserver, never()).onFirmwareNumberChange(plcData);
            }
        }

        @Nested @DisplayName("TOOL DATA SOURCE")
        class ToolDataSource {
            @Test @DisplayName("adds observer")
            void addsObserver(){
                ToolDataObserver toolDataObserver = Mockito.mock(ToolDataObserver.class);

                plcData.registerToolDataObserver(toolDataObserver);
                plcData.notifyToolDataObservers();

                verify(toolDataObserver, times(1)).onToolDataChange(plcData);
            }

            @Test @DisplayName("removes observer")
            void removesObserver(){
                ToolDataObserver toolDataObserver = Mockito.mock(ToolDataObserver.class);

                plcData.registerToolDataObserver(toolDataObserver);
                plcData.removeToolDataObserver(toolDataObserver);
                plcData.notifyToolDataObservers();

                verify(toolDataObserver, never()).onToolDataChange(plcData);
            }
        }


        @Nested @DisplayName("MEASURED CURVE SOURCE")
        class MeasuredCurveSource{
            @Test @DisplayName("adds observer")
            void addsObserver(){
                MeasuredCurveObserver measuredCurveObserver = Mockito.mock(MeasuredCurveObserver.class);

                plcData.registerMeasuredCurveObserver(measuredCurveObserver);
                plcData.notifyMeasuredCurveObservers();

                verify(measuredCurveObserver, times(1)).onMeasuredCurveChange(plcData);
            }

            @Test @DisplayName("removes observer")
            void removesObserver(){
                MeasuredCurveObserver measuredCurveObserver = Mockito.mock(MeasuredCurveObserver.class);

                plcData.registerMeasuredCurveObserver(measuredCurveObserver);
                plcData.removeMeasuredCurveObserver(measuredCurveObserver);
                plcData.notifyMeasuredCurveObservers();

                verify(measuredCurveObserver, never()).onMeasuredCurveChange(plcData);
            }
        }

        @Nested @DisplayName("MOTOR CURVE SOURCE")
        class MotorCurveSource{
            @Test @DisplayName("adds observer")
            void addsObserver(){
                MotorCurveObserver motorCurveObserver = Mockito.mock(MotorCurveObserver.class);

                plcData.registerMotorCurveObserver(motorCurveObserver);
                plcData.notifyMotorCurveObservers();

                verify(motorCurveObserver, times(1)).onMotorCurveChange(plcData);
            }

            @Test @DisplayName("removes observer")
            void removesObserver(){
                MotorCurveObserver motorCurveObserver = Mockito.mock(MotorCurveObserver.class);

                plcData.registerMotorCurveObserver(motorCurveObserver);
                plcData.removeMotorCurveObserver(motorCurveObserver);
                plcData.notifyMotorCurveObservers();

                verify(motorCurveObserver, never()).onMotorCurveChange(plcData);
            }
        }

        @Nested @DisplayName("CONNECTION STATUS SOURCE")
        class ConnectionStatusSource{
            @Test @DisplayName("adds observer")
            void addsObserver(){
                ConnectionStatusObserver connectionStatusObserver = Mockito.mock(ConnectionStatusObserver.class);

                plcData.registerConnectionStatusObserver(connectionStatusObserver);
                plcData.notifyConnectionStatusObservers();

                verify(connectionStatusObserver, times(1)).onConnectionStatusChange(plcData);
            }

            @Test @DisplayName("removes observer")
            void removesObserver(){
                ConnectionStatusObserver connectionStatusObserver = Mockito.mock(ConnectionStatusObserver.class);

                plcData.registerConnectionStatusObserver(connectionStatusObserver);
                plcData.removeConnectionStatusObserver(connectionStatusObserver);
                plcData.notifyConnectionStatusObservers();

                verify(connectionStatusObserver, never()).onConnectionStatusChange(plcData);
            }
        }
    }
}
