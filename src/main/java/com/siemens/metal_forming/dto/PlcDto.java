package com.siemens.metal_forming.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.siemens.metal_forming.annotations.ValidIpAddress;
import com.siemens.metal_forming.enumerated.ConnectionStatus;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import javax.validation.constraints.NotBlank;

public enum PlcDto {;

    public enum Request{;
        @Value @Builder(toBuilder = true) @ToString
        public static class Create{
            @ValidIpAddress
            String ipAddress;

            @NotBlank(message = "Name must be filled")
            String name;
        }

        @Value @Builder(toBuilder = true)@ToString
        public static class Update{
            @ValidIpAddress
            String ipAddress;

            @NotBlank(message = "Name must be filled")
            String name;
        }
    }

    public enum Response{;
        @Value @Builder(toBuilder = true) @ToString
        public static class Overview{
            Long id;
            String name;
            String ipAddress;
            HardwareInformationDto hardwareInformation;
            ConnectionDto connection;
        }

        @Value @Builder @JsonDeserialize(builder = Connection.ConnectionBuilder.class) @ToString
        public static class Connection{
            Long id;
            ConnectionStatus connectionStatus;
        }

        @Value @Builder @ToString
        public static class CurrentTool{
            Long id;
            Integer toolNumber;
        }

        @Value @Builder @ToString
        public static class NewTool{
            Long id;
            ToolDto.Response.Overview newTool;
        }
    }
}
