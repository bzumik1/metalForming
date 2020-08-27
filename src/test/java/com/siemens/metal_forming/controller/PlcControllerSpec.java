package com.siemens.metal_forming.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.metal_forming.dto.DtoMapper;
import com.siemens.metal_forming.dto.PlcDto;
import com.siemens.metal_forming.entity.Plc;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;
import com.siemens.metal_forming.exception.exceptionsApi.ApiException;
import com.siemens.metal_forming.service.PlcService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @MockBean
    private DtoMapper dtoMapper;

    private final String path = "/plcs";

    @BeforeEach
    void initialize(){
        Mockito.reset(plcService);
        Mockito.reset(dtoMapper);
    }

    @Nested @DisplayName("GET ALL PLCS")
    class GetAllPlcs{
        @Test @DisplayName("triggers plcService.findAll()")
        void triggersPlcService() throws Exception {
            mvc.perform(get(path)
                .param("page", "0")
                .param("size", "10"));

            ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

            Mockito.verify(plcService,times(1)).findAll();
        }

        @Test @DisplayName("returns 200 Ok when everything is ok")
        void returnsOkWhenEverythingIsOk() throws Exception {
            mvc.perform(get(path)
                    .param("page", "0")
                    .param("size", "10"))
                    .andExpect(status().isOk());
        }

        @Test @DisplayName("triggers DtoMapper and transforms all plcs to proper DTO")
        void triggersDtoMapper() throws Exception {
            List<Plc> plcsToReturn = List.of(Plc.builder().ipAddress("192.168.0.1").build(),Plc.builder().ipAddress("192.168.0.2").build());
            Mockito.when(plcService.findAll()).thenReturn(plcsToReturn);

            mvc.perform(get(path)
                    .param("page","0")
                    .param("size","10"));

            Mockito.verify(dtoMapper,times(2)).toPlcDtoOverview(any(Plc.class));
        }
    }

    @Nested @DisplayName("CREATE PLC")
    class CreatePlc{
        PlcDto.Request.Create validPlcDto;

        @BeforeEach
        void initialize(){
            validPlcDto = PlcDto.Request.Create.builder().ipAddress("192.168.0.1").name("name").build();
        }

        @Nested @DisplayName("VALID PLC")
        class ValidPlc{
            String validPlcDtoJson;

            @BeforeEach
            void initializeForValidPlc() throws JsonProcessingException {
                validPlcDtoJson = objectMapper.writeValueAsString(validPlcDto);
                Mockito.when(dtoMapper.toPlc(any(PlcDto.Request.Create.class))).thenReturn(Plc.builder().ipAddress("192.168.0.1").name("name").build());
            }

            @Test @DisplayName("triggers plcService.createPlc() when PLC was valid")
            void triggersPlcServiceWhenValid() throws Exception {
                mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson));

                Mockito.verify(plcService,Mockito.times(1)).create(any(Plc.class));
            }

            @Test @DisplayName("returns 201 Created when everything was ok")
            void returnsCreatedWhenEverythingWasOk() throws Exception {
                mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andExpect(status().isCreated());
            }

            @Test @DisplayName("triggers DtoMapper and transforms incoming DTO to correct PLC")
            void sendsBackPlcWithId() throws Exception {
                mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson));

                Mockito.verify(dtoMapper,times(1)).toPlc(any(PlcDto.Request.Create.class));
            }

            @Test @DisplayName("returns 409 Conflict when PLC with same IP address already exists")
            void returnsConflictWhenPlcWithSameIpAddressAlreadyExists() throws Exception {
                Mockito.when(plcService.create(any(Plc.class))).thenThrow(new PlcUniqueConstrainException("Plc with given IP address "+ validPlcDto.getIpAddress()+" already exists"));

                mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andExpect(status().isConflict());
            }

            @Test @DisplayName("returns 409 Conflict when PLC with same name already exists")
            void returnsConflictWhenPlcWithSameNameAlreadyExists() throws Exception {
                Mockito.when(plcService.create(any(Plc.class))).thenThrow(new PlcUniqueConstrainException("Plc with given name "+validPlcDto.getName()+" already exists"));

                mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andExpect(status().isConflict());
            }

            @Test @DisplayName("returns correct body with api exception when PLC already exists")
            void returnsCorrectBodyWithApiExceptionWhenPlcAlreadyExists() throws Exception {
                Mockito.when(plcService.create(any(Plc.class))).thenThrow(new PlcUniqueConstrainException("Plc with given IP address "+ validPlcDto.getIpAddress()+" already exists"));

                MvcResult mvcResult = mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andReturn();

                assertThat(mvcResult.getResponse().getContentAsString())
                        .contains("Plc with given IP address 192.168.0.1 already exists")
                        .contains("CONFLICT");
            }

            @Test @DisplayName("triggers DtoMapper and transforms newly created plc to proper DTO")
            void triggersDtoMapper() throws Exception {
                Plc plcToReturn = DtoMapper.INSTANCE.toPlc(validPlcDto).toBuilder().id(1L).build();
                Mockito.when(plcService.create(any(Plc.class))).thenReturn(plcToReturn);


                mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson));

                Mockito.verify(dtoMapper,times(1)).toPlcDtoOverview(any(Plc.class));
            }
        }

        @Nested @DisplayName("INVALID PLC")
        class InvalidPlc{
            @Test @DisplayName("returns 400 Bad Request when PLC is invalid")
            void returnsBadRequestWhenPlcIsInvalid() throws Exception {
                PlcDto.Request.Create invalidPlcDto = validPlcDto.toBuilder().name(null).build();
                String invalidPlcJson = objectMapper.writeValueAsString(invalidPlcDto);

                mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPlcJson))
                        .andExpect(status().isBadRequest());
            }

            @Test @DisplayName("returns correct body with api exception when PLC is invalid")
            void returnsCorrectBodyWithApiExceptionWhenPlcIsInvalid() throws Exception {
                PlcDto.Request.Create invalidPlcDto = validPlcDto.toBuilder().name(null).ipAddress(null).build();
                String invalidPlcJson = objectMapper.writeValueAsString(invalidPlcDto);

                MvcResult mvcResult = mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPlcJson))
                        .andReturn();

                assertThat(mvcResult.getResponse().getContentAsString())
                        .contains("Name must be filled")
                        .contains("IP address must be filled")
                        .contains("BAD_REQUEST");
            }
        }
    }

    @Nested @DisplayName("UPDATE PLC")
    class UpdatePlc{
        PlcDto.Request.Update validPlcDto;
        final String updatePath = path+"/{id}";

        @BeforeEach
        void initialize(){
            validPlcDto = PlcDto.Request.Update.builder().ipAddress("192.168.0.1").name("name").build();
        }

        @Nested @DisplayName("VALID PLC")
        class ValidPlc{
            String validPlcDtoJson;

            @BeforeEach
            void initializeForValidPlc() throws JsonProcessingException {
                validPlcDtoJson = objectMapper.writeValueAsString(validPlcDto);
            }

            @Test @DisplayName("triggers plcService.updateById() when PLC was valid")
            void triggersPlcServiceWhenValid() throws Exception {
                mvc.perform(put(updatePath,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson));

                Mockito.verify(plcService,Mockito.times(1))
                        .updateById(any(Long.class),any(Consumer.class));
            }

            @Test @DisplayName("returns 200 Ok when everything was ok")
            void returnsCreatedWhenEverythingWasOk() throws Exception {
                mvc.perform(put(updatePath,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andExpect(status().isOk());
            }

            @Test @DisplayName("returns 409 Conflict when PLC with same IP address already exists")
            void returnsConflictWhenPlcWithSameIpAddressAlreadyExists() throws Exception {
                Mockito.when(plcService.updateById(any(Long.class),any(Consumer.class))).thenThrow(new PlcUniqueConstrainException("Plc with given IP address "+ validPlcDto.getIpAddress()+" already exists"));

                mvc.perform(put(updatePath,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andExpect(status().isConflict());
            }

            @Test @DisplayName("returns correct body with api exception when PLC already exists")
            void returnsCorrectBodyWithApiExceptionWhenPlcAlreadyExists() throws Exception {
                Mockito.when(plcService.updateById(any(Long.class),any(Consumer.class))).thenThrow(new PlcUniqueConstrainException("Plc with given IP address "+ validPlcDto.getIpAddress()+" already exists"));

                MvcResult mvcResult = mvc.perform(put(updatePath,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andReturn();

                assertThat(mvcResult.getResponse().getContentAsString())
                        .contains("Plc with given IP address 192.168.0.1 already exists")
                        .contains("CONFLICT");
            }

            @Test @DisplayName("triggers DtoMapper and transforms updated plc to proper DTO")
            void triggersDtoMapper() throws Exception {
                Plc plcToReturn = new Plc();
                Mockito.when(plcService.updateById(any(Long.class),any(Consumer.class))).thenReturn(plcToReturn);


                mvc.perform(put(updatePath,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson));

                Mockito.verify(dtoMapper,times(1)).toPlcDtoOverview(any(Plc.class));
            }
        }


        @Nested @DisplayName("INVALID PLC")
        class InvalidPlc{
            @Test @DisplayName("returns 400 Bad Request when PLC is invalid")
            void returnsBadRequestWhenPlcIsInvalid() throws Exception {
                PlcDto.Request.Update invalidPlcDto = validPlcDto.toBuilder().name(null).build();
                String invalidPlcJson = objectMapper.writeValueAsString(invalidPlcDto);

                mvc.perform(put(path+"/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPlcJson))
                        .andExpect(status().isBadRequest());
            }

            @Test @DisplayName("returns correct body with api exception when PLC is invalid")
            void returnsCorrectBodyWithApiExceptionWhenPlcIsInvalid() throws Exception {
                PlcDto.Request.Update invalidPlcDto = validPlcDto.toBuilder().name(null).ipAddress(null).build();
                String invalidPlcJson = objectMapper.writeValueAsString(invalidPlcDto);

                MvcResult mvcResult = mvc.perform(put(path+"/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPlcJson))
                        .andReturn();

                assertThat(mvcResult.getResponse().getContentAsString())
                        .contains("Name must be filled")
                        .contains("IP address must be filled")
                        .contains("BAD_REQUEST");
            }
        }
    }

    @Nested @DisplayName("DELETE PLC BY ID")
    class DeletePlcById{
        @Test @DisplayName("triggers plcService.deleteById(id)")
        void triggersPlcService() throws Exception {
            mvc.perform(delete(path+"/{id}",1L));

            Mockito.verify(plcService,Mockito.times(1)).deleteById(1L);
        }

        @Test @DisplayName("returns 200 Ok when everithing was ok")
        void returnsOkWhenEverythingWasOk() throws Exception {
            mvc.perform(delete(path+"/{id}",1L))
                    .andExpect(status().isOk());
        }

        @Test @DisplayName("returns 404 Not Found when PLC which should be deleted was not found")
        void returnsNotFoundWhenPlcWhichShouldBeDeletedWasNotFound() throws Exception {
            doThrow(new PlcNotFoundException(1L)).when(plcService).deleteById(1L);

            mvc.perform(delete(path+"/{id}",1L))
                    .andExpect(status().isNotFound());
        }

        @Test @DisplayName("returns proper api exception when PLC which should be deleted was not found")
        void returnsProperApiExceptionWhenPlcWhichShouldBeDeletedWasNotFound() throws Exception {
            doThrow(new PlcNotFoundException(1L)).when(plcService).deleteById(1L);
            ApiException expectedApiException = ApiException.builder()
                    .message("Plc with id 1 was not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();

            MvcResult mvcResult = mvc.perform(delete(path+"/{id}",1L))
                    .andReturn();

            assertThat(mvcResult.getResponse().getContentAsString())
                    .contains(expectedApiException.getMessage())
                    .contains(expectedApiException.getStatus().name());
        }
    }


}