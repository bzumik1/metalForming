package com.siemens.metal_forming.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.metal_forming.dto.PlcDto;
import com.siemens.metal_forming.exception.exceptions.PlcNotFoundException;
import com.siemens.metal_forming.exception.exceptions.PlcUniqueConstrainException;
import com.siemens.metal_forming.exception.exceptionsApi.ApiException;
import com.siemens.metal_forming.service.PlcService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
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

    private final String path = "/api/plcs";

    @BeforeEach
    void initialize(){
        Mockito.reset(plcService);
    }

    @Nested @DisplayName("GET ALL PLCS")
    class GetAllPlcs{
        @Test @DisplayName("returns plcs in database")
        void triggersPlcService() throws Exception {
            PlcDto.Response.Overview plcInDatabase1 = PlcDto.Response.Overview.builder().id(1L).build();
            PlcDto.Response.Overview plcInDatabase2 = PlcDto.Response.Overview.builder().id(2L).build();

            when(plcService.findAll()).thenReturn(List.of(plcInDatabase1, plcInDatabase2));

            MvcResult mvcResult = mvc.perform(get(path)).andReturn();

            assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(List.of(plcInDatabase1, plcInDatabase2)));
        }

        @Test @DisplayName("returns 200 Ok when everything is ok")
        void returnsOkWhenEverythingIsOk() throws Exception {
            mvc.perform(get(path)
                    .param("page", "0")
                    .param("size", "10"))
                    .andExpect(status().isOk());
        }


        @ParameterizedTest
        @ValueSource(strings = {"http://localhost:4200", "http://localhost:4201","http://random-page.com"})
        @DisplayName("allows CORS from all origins")
        void allowsCorsFromAllOrigins(String origin) throws Exception {
            mvc.perform(options(path)
                    .header("Access-Control-Request-Method", "GET")
                    .header("Origin", origin))
                    .andExpect(status().isOk()); //when cors doesn't work returns 403 Forbidden
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
            }

            @Test @DisplayName("triggers plcService.createPlc() when PLC was valid")
            void triggersPlcServiceWhenValid() throws Exception {

                mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson));

                Mockito.verify(plcService,Mockito.times(1)).createPlc(any());
            }

            @Test @DisplayName("returns 201 Created when everything was ok")
            void returnsCreatedWhenEverythingWasOk() throws Exception {
                mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andExpect(status().isCreated());
            }


            @Test @DisplayName("returns 409 Conflict when PLC with same IP address already exists")
            void returnsConflictWhenPlcWithSameIpAddressAlreadyExists() throws Exception {
                Mockito.when(plcService.createPlc(any())).thenThrow(new PlcUniqueConstrainException("Plc with given IP address "+ validPlcDto.getIpAddress()+" already exists"));

                mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andExpect(status().isConflict());
            }

            @Test @DisplayName("returns 409 Conflict when PLC with same name already exists")
            void returnsConflictWhenPlcWithSameNameAlreadyExists() throws Exception {
                Mockito.when(plcService.createPlc(any())).thenThrow(new PlcUniqueConstrainException("Plc with given name "+validPlcDto.getName()+" already exists"));

                mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andExpect(status().isConflict());
            }

            @Test @DisplayName("returns correct body with api exception when PLC already exists")
            void returnsCorrectBodyWithApiExceptionWhenPlcAlreadyExists() throws Exception {
                Mockito.when(plcService.createPlc(any())).thenThrow(new PlcUniqueConstrainException("Plc with given IP address "+ validPlcDto.getIpAddress()+" already exists"));

                MvcResult mvcResult = mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andReturn();

                assertThat(mvcResult.getResponse().getContentAsString())
                        .contains("Plc with given IP address 192.168.0.1 already exists")
                        .contains("CONFLICT");
            }

            @Test @DisplayName("returns newly created plc")
            void triggersDtoMapper() throws Exception {
                PlcDto.Response.Overview plcDtoToReturn = PlcDto.Response.Overview.builder().name("newPlc").build();
                Mockito.when(plcService.createPlc(any())).thenReturn(plcDtoToReturn);


                MvcResult mvcResult = mvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson)).andReturn();

                assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(plcDtoToReturn));
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

        @ParameterizedTest
        @ValueSource(strings = {"http://localhost:4200", "http://localhost:4201","http://random-page.com"})
        @DisplayName("allows CORS from all origins")
        void allowsCorsFromAllOrigins(String origin) throws Exception {
            mvc.perform(options(path)
                    .header("Access-Control-Request-Method", "POST")
                    .header("Origin", origin))
                    .andExpect(status().isOk()); //when cors doesn't work returns 403 Forbidden
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

            @Test @DisplayName("returns updated plc")
            void triggersPlcServiceWhenValid() throws Exception {
                PlcDto.Response.Overview plcDtoToReturn = PlcDto.Response.Overview.builder().name("updatedPlc").build();

                when(plcService.update(1L, validPlcDto)).thenReturn(plcDtoToReturn);

                MvcResult mvcResult = mvc.perform(put(updatePath,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson)).andReturn();

                assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(plcDtoToReturn));
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
                Mockito.when(plcService.update(1L,validPlcDto)).thenThrow(new PlcUniqueConstrainException("Plc with given IP address "+ validPlcDto.getIpAddress()+" already exists"));

                mvc.perform(put(updatePath,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andExpect(status().isConflict());
            }

            @Test @DisplayName("returns 404 Not found when plc with given id was not found")
            void returnsNotFoundWhenToolDoesNotExist() throws Exception {
                Mockito.when(plcService.update(1L,validPlcDto)).thenThrow(new PlcNotFoundException(2L));

                mvc.perform(put(updatePath,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andExpect(status().isNotFound());
            }

            @Test @DisplayName("returns correct body with api exception when PLC already exists")
            void returnsCorrectBodyWithApiExceptionWhenPlcAlreadyExists() throws Exception {
                Mockito.when(plcService.update(1L,validPlcDto)).thenThrow(new PlcUniqueConstrainException("Plc with given IP address "+ validPlcDto.getIpAddress()+" already exists"));

                MvcResult mvcResult = mvc.perform(put(updatePath,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validPlcDtoJson))
                        .andReturn();

                assertThat(mvcResult.getResponse().getContentAsString())
                        .contains("Plc with given IP address 192.168.0.1 already exists")
                        .contains("CONFLICT");
            }
        }


        @Nested @DisplayName("INVALID PLC")
        class InvalidPlc{
            @Test @DisplayName("returns 400 Bad Request when PLC is invalid")
            void returnsBadRequestWhenPlcIsInvalid() throws Exception {
                PlcDto.Request.Update invalidPlcDto = validPlcDto.toBuilder().name(null).build();
                String invalidPlcJson = objectMapper.writeValueAsString(invalidPlcDto);

                mvc.perform(put(updatePath,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPlcJson))
                        .andExpect(status().isBadRequest());
            }

            @Test @DisplayName("returns correct body with api exception when PLC is invalid")
            void returnsCorrectBodyWithApiExceptionWhenPlcIsInvalid() throws Exception {
                PlcDto.Request.Update invalidPlcDto = validPlcDto.toBuilder().name(null).ipAddress(null).build();
                String invalidPlcJson = objectMapper.writeValueAsString(invalidPlcDto);

                MvcResult mvcResult = mvc.perform(put(updatePath,1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPlcJson))
                        .andReturn();

                assertThat(mvcResult.getResponse().getContentAsString())
                        .contains("Name must be filled")
                        .contains("IP address must be filled")
                        .contains("BAD_REQUEST");
            }
        }

        @ParameterizedTest
        @ValueSource(strings = {"http://localhost:4200", "http://localhost:4201","http://random-page.com"})
        @DisplayName("allows CORS from all origins")
        void allowsCorsFromAllOrigins(String origin) throws Exception {
            mvc.perform(options(updatePath,1L)
                    .header("Access-Control-Request-Method", "PUT")
                    .header("Origin", origin))
                    .andExpect(status().isOk()); //when cors doesn't work returns 403 Forbidden
        }
    }

    @Nested @DisplayName("DELETE PLC BY ID")
    class DeletePlcById{
        @Test @DisplayName("triggers plcService.deleteById(id)")
        void triggersPlcService() throws Exception {
            mvc.perform(delete(path+"/{id}",1L));

            Mockito.verify(plcService,Mockito.times(1)).delete(1L);
        }

        @Test @DisplayName("returns 200 Ok when everything was ok")
        void returnsOkWhenEverythingWasOk() throws Exception {
            mvc.perform(delete(path+"/{id}",1L))
                    .andExpect(status().isOk());
        }

        @Test @DisplayName("returns 404 Not Found when PLC which should be deleted was not found")
        void returnsNotFoundWhenPlcWhichShouldBeDeletedWasNotFound() throws Exception {
            doThrow(new PlcNotFoundException(1L)).when(plcService).delete(1L);

            mvc.perform(delete(path+"/{id}",1L))
                    .andExpect(status().isNotFound());
        }

        @Test @DisplayName("returns proper api exception when PLC which should be deleted was not found")
        void returnsProperApiExceptionWhenPlcWhichShouldBeDeletedWasNotFound() throws Exception {
            doThrow(new PlcNotFoundException(1L)).when(plcService).delete(1L);
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

        @ParameterizedTest
        @ValueSource(strings = {"http://localhost:4200", "http://localhost:4201","http://random-page.com"})
        @DisplayName("allows CORS from all origins")
        void allowsCorsFromAllOrigins(String origin) throws Exception {
            mvc.perform(options(path+"/{id}",1L)
                    .header("Access-Control-Request-Method", "DELETE")
                    .header("Origin", origin))
                    .andExpect(status().isOk()); //when cors doesn't work returns 403 Forbidden
        }
    }
}
