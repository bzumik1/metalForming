package com.siemens.metal_forming.service;

import com.siemens.metal_forming.entity.log.Log;
import com.siemens.metal_forming.exception.exceptions.LogNotFoundException;
import com.siemens.metal_forming.repository.LogRepository;
import com.siemens.metal_forming.service.impl.LogServiceImpl;
import com.siemens.metal_forming.testBuilders.TestLogBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("<= LOG SERVICE SPECIFICATION =>")
public class LogServiceSpec {
    @Mock
    LogRepository logRepository;

    LogService logService;

    @Captor
    ArgumentCaptor<Log> logCaptor;

    @BeforeEach
    void initialize(){
        logService = new LogServiceImpl(logRepository);
    }


    @Nested @DisplayName("FIND ALL BY TOOL ID")
    class FindAllByToolId{
        @Test @DisplayName("triggers findAllByToolInformationToolIdOrderByCreatedOnDesc()")
        void triggersLogRepository(){
            logService.findAllByToolId(1L);

            verify(logRepository,times(1)).findAllByToolInformationToolIdOrderByCreatedOnDesc(1L);
        }
    }

    @Nested @DisplayName("FIND LOG BY ID")
    class FindLogById{
        @Test @DisplayName("triggers logRepository.findById")
        void triggersLogRepository(){
            when(logRepository.findById(1L)).thenReturn(Optional.of(Log.builder().build()));

            logService.findById(1L);

            verify(logRepository, times(1)).findById(1L);
        }

        @Test @DisplayName("throws exception when log was not found")
        void throwsExceptionWhenLogWasNotFound(){
            when(logRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(LogNotFoundException.class, () -> logService.findById(1L));
        }
    }

    @Nested @DisplayName("CREATE LOG")
    class CreateLog{
        @Test @DisplayName("saves given log to database")
        void savesGivenLogToDatabase(){
            TestLogBuilder testLogBuilder = new TestLogBuilder();
            Log logToBeSaved = testLogBuilder.build();

            logService.save(logToBeSaved);

            verify(logRepository, times(1)).save(logToBeSaved);
        }

        @Test @DisplayName("returns saved log")
        void returnsSavedLog(){
            TestLogBuilder testLogBuilder = new TestLogBuilder();
            Log logToBeSaved = testLogBuilder.build();

            when(logRepository.save(logToBeSaved)).thenAnswer(invocation -> invocation.getArgument(0));

            assertThat(logService.save(logToBeSaved)).isEqualTo(logToBeSaved);
        }

    }

    @Nested @DisplayName("DELETE ALL BY TOOL IDS")
    class DeleteAllByToolIds{
        @Test @DisplayName("throws LogNotFoundException when log with given id was not found")
        void throwsExceptionWhenLogWithGivenIdWasNotFound(){
            when(logRepository.findIdsByIdsIn(Set.of(1L,2L))).thenReturn(Set.of(1L));

            assertThrows(LogNotFoundException.class,() -> logService.delete(Set.of(1L,2L)));
        }

        @Test @DisplayName("deletes logs which were in database and then throws LogNotFoundException when some were missing")
        void deletesLogsWhichWereInDatabaseAndThenThrowsExceptionWhenSomeWereMissing(){
            when(logRepository.findIdsByIdsIn(Set.of(1L,2L))).thenReturn(Set.of(1L));

            assertThrows(LogNotFoundException.class,() -> logService.delete(Set.of(1L,2L)));
            verify(logRepository, times(1)).deleteAllByIdIn(Set.of(1L));
        }
    }

    @Nested @DisplayName("ADD COMMENT BY ID")
    class AddCommentById{
        @Test @DisplayName("throws LogNotFoundException when log with given id was not found")
        void throwsExceptionWhenLogWasNotFound(){
            when(logRepository.findById(1L)).thenReturn(Optional.empty());

            assertThrows(LogNotFoundException.class, () -> logService.updateComment(1L, "Update"));
        }

        @Test @DisplayName("returns log with comment")
        void returnsLogWithComment(){
            TestLogBuilder testLogBuilder = new TestLogBuilder();

            when(logRepository.findById(1L)).thenReturn(Optional.of(testLogBuilder.id(1L).build()));
            when(logRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

            assertThat(logService.updateComment(1L,"new comment").getComment()).isEqualTo("new comment");
        }

        @Test @DisplayName("saves log with comment to database")
        void savesLogWithCommentToDatabase(){
            TestLogBuilder testLogBuilder = new TestLogBuilder();
            Log logInDb = testLogBuilder.id(1L).build();

            when(logRepository.findById(1L)).thenReturn(Optional.of(logInDb));

            logService.updateComment(1L, "new comment");

            verify(logRepository, times(1)).save(logCaptor.capture());
            assertThat(logCaptor.getValue().getComment())
                    .as("log should be saved with comment")
                    .isEqualTo("new comment");
        }
    }

}
