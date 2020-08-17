package com.siemens.metal_forming;

import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.opcua.OpcuaClient;
import com.siemens.metal_forming.opcua.OpcuaClientProvider;
import com.siemens.metal_forming.opcua.OpcuaConnector;
import com.siemens.metal_forming.service.PlcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Optional;

@SpringBootApplication @Slf4j
public class MetalFormingApplication {



	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MetalFormingApplication.class, args);
		log.info("Using {} as implementation for OpcuaClient, this is based on application.properties",context.getBean(OpcuaClientProvider.class).getClass());
	}






}
