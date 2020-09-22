package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.annotations.ValidIpAddress;
import com.siemens.metal_forming.entity.Connection;
import com.siemens.metal_forming.entity.HardwareInformation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public enum PlcDto {;

    public enum Request{;
        @Value @Builder(toBuilder = true)
        public static class Create{
            @ValidIpAddress
            String ipAddress;

            @NotBlank(message = "Name must be filled")
            String name;
        }

        @Value @Builder(toBuilder = true)
        public static class Update{
            @ValidIpAddress
            String ipAddress;

            @NotBlank(message = "Name must be filled")
            String name;
        }
    }

    public enum Response{;
        @Value @Builder(toBuilder = true)
        public static class Overview{
            Long id;
            String name;
            String ipAddress;
            HardwareInformationDto hardwareInformation;
            ConnectionDto connection;
        }

        @Value
        public static class Another{
        }
    }
}
