package com.siemens.metal_forming.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.service.PlcService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(controllers = PlcController.class)
@DisplayName("<= PLC CONTROLLER SPECIFICATION =>")
public class PlcControllerSpec {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlcService plcService;

    @Test @DisplayName("returns bad request when PLC is invalid")
    void returnsBadRequestWhenPlcIsInvalid() throws Exception {
        Plc plc = Plc.builder().build();
        String plcJson = objectMapper.writeValueAsString(plc);

        log.info("Received JSON is: {}",plcJson);

        mvc.perform(post("/plcs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(plcJson))
                .andExpect(status().isBadRequest());
    }

    @Test @DisplayName("triggers plcService.createPlc() when PLC was valid")
    void triggersPlcServiceWhenValid() throws Exception {
        Plc validPlc = Plc.builder().ipAddress("192.168.0.1").build();
        String validPlcJson = objectMapper.writeValueAsString(validPlc);

        mvc.perform(post("/plcs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validPlcJson));
        ArgumentCaptor<Plc> plcCaptor = ArgumentCaptor.forClass(Plc.class);

        Mockito.verify(plcService,Mockito.times(1)).create(plcCaptor.capture());
    }

    @Test @DisplayName("sends back same plc but with filled ID")
    void sendsBackPlcWithId() throws Exception {
        Plc validPlc = Plc.builder().ipAddress("192.168.0.1").build();
        String validPlcJson = objectMapper.writeValueAsString(validPlc);

        Plc returnPlc = Plc.builder().ipAddress("192.168.0.1").id(1L).build();
        String returnPlcJson = objectMapper.writeValueAsString(returnPlc);

        Mockito.when(plcService.create(any(Plc.class))).thenReturn(returnPlc);


        MvcResult mvcResult = mvc.perform(post("/plcs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validPlcJson))
                .andReturn();



        String responsePlcJson = mvcResult.getResponse().getContentAsString();

        assertThat(responsePlcJson).isEqualTo(returnPlcJson);
    }
}
