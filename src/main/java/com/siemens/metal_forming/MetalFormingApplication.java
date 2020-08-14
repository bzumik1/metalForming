package com.siemens.metal_forming;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import com.siemens.metal_forming.service.PlcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class MetalFormingApplication {



	public static void main(String[] args) {
		SpringApplication.run(MetalFormingApplication.class, args);

	}






}
