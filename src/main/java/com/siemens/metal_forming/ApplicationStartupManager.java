package com.siemens.metal_forming;

import com.siemens.metal_forming.connection.PlcConnector;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.repository.PlcRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ApplicationStartupManager {
    private final PlcRepository plcRepository;
    private final PlcConnector plcConnector;

    @Autowired
    public ApplicationStartupManager(PlcRepository plcRepository, PlcConnector plcConnector) {
        this.plcRepository = plcRepository;
        this.plcConnector = plcConnector;
    }


    @EventListener @Transactional //ToDo should fetch all from db?
    public void onApplicationStartup(ContextRefreshedEvent event) {
        List<Plc> plcs = plcRepository.findAll().stream()
                .map(plc -> CompletableFuture.supplyAsync(() -> plcConnector.connect(plc)))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        log.info("Trying to connect to plcs with IP addresses: {} over OPC UA", plcs.stream().map(Plc::getIpAddress).collect(Collectors.joining(", ")));
        log.info("Plcs with IP addresses: {} were successfully connected.", plcs.stream().filter(Plc::isConnected).map(Plc::getIpAddress).collect(Collectors.joining(", ")));
        plcRepository.saveAll(plcs);
    }
}
