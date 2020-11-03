package com.siemens.metal_forming;

import com.siemens.metal_forming.service.PlcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationStartupManager {
    private final PlcService plcService;

    @Autowired
    public ApplicationStartupManager(PlcService plcService) {
        this.plcService = plcService;
    }

    @EventListener
    public void onApplicationStartup(ContextRefreshedEvent event) {
        plcService.connectAllPlcsInDatabase();
    }
}
