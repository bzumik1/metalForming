package com.siemens.metal_forming.dto;

import com.siemens.metal_forming.entity.Connection;
import com.siemens.metal_forming.entity.HardwareInformation;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public enum PlcDto {;

    public enum Request{;
        @Value @Builder(toBuilder = true)
        public static class Create{
            @NotBlank(message = "IP address must be filled")
            @Pattern(regexp = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\." +
                    "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])",
                    message = "IP address must be in correct format")
            String ipAddress;

            @NotBlank(message = "Name must be filled")
            String name;
        }

        @Value @Builder(toBuilder = true)
        public static class Update{
            @NotBlank(message = "IP address must be filled")
            @Pattern(regexp = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\." +
                    "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])",
                    message = "IP address must be in correct format")
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
