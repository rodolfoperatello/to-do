package br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task;

import static br.com.bmtptecnologia.to_do.templates.task.CreateTaskRequestTemplate.buildCreateTaskRequestWithInvalidFields;
import static br.com.bmtptecnologia.to_do.templates.task.CreateTaskRequestTemplate.buildDefaultCreateTaskRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.bmtptecnologia.to_do.application.task.dto.CreateTaskDTO;
import br.com.bmtptecnologia.to_do.application.task.exception.CouldNotCreateTaskException;
import br.com.bmtptecnologia.to_do.application.task.usecases.CreateTaskUseCase;
import br.com.bmtptecnologia.to_do.config.ComponentTest;
import br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.request.CreateTaskRequest;
import br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.response.CreatedTaskResponse;

@ComponentTest
@AutoConfigureMockMvc
class TaskControllerTest {
   @Autowired
   private MockMvc mockMvc;
   @SpyBean
   private CreateTaskUseCase createTaskUseCase;
   @Autowired
   private ObjectMapper objectMapper;
   private static final String URI = "/api/v1/task";

   @Test
   public void mustReturn2xxWhenTaskIsCreated() throws Exception {
      // arrange
      CreateTaskRequest createTaskRequest = buildDefaultCreateTaskRequest();

      String requestJson = this.objectMapper.writeValueAsString(createTaskRequest);

      // act // assert
      MvcResult mvcResult = this.mockMvc.perform(
              post(URI)
                  .contentType(APPLICATION_JSON)
                  .content(requestJson))
          .andExpect(MockMvcResultMatchers.status().isCreated())
          .andReturn();

      String responseAsString = mvcResult.getResponse().getContentAsString();

      CreatedTaskResponse createdTaskResponse = this.objectMapper.readValue(responseAsString,
          CreatedTaskResponse.class);

      assertNotNull(createdTaskResponse);
      assertEquals(1L, createdTaskResponse.getTaskId());
      assertEquals("Clean my car", createdTaskResponse.getDescription());
      assertEquals("CREATED", createdTaskResponse.getTaskStatus());
      assertEquals(LocalDate.now(), createdTaskResponse.getCreatedAt().toLocalDate());
      assertEquals(LocalDate.now(), createdTaskResponse.getUpdatedAt().toLocalDate());
   }

   @Test
   public void mustReturn4xxWhenRequestIsInvalid() throws Exception {
      // arrange
      CreateTaskRequest createTaskRequest = buildCreateTaskRequestWithInvalidFields();

      String requestJson = this.objectMapper.writeValueAsString(createTaskRequest);

      // act
      this.mockMvc.perform(post(URI)
              .contentType(APPLICATION_JSON)
              .content(requestJson))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andReturn();
   }

   @Test
   public void mustReturn5xxWhenCreateTaskProcessThrowsException() throws Exception {
      // arrange
      doThrow(CouldNotCreateTaskException.class).when(createTaskUseCase).execute(
          any(CreateTaskDTO.class));

      CreateTaskRequest createTaskRequest = buildDefaultCreateTaskRequest();

      String requestJson = objectMapper.writeValueAsString(createTaskRequest);

      // act
      MvcResult mvcResult = this.mockMvc.perform(post(URI)
              .contentType(APPLICATION_JSON)
              .content(requestJson))
          .andExpect(MockMvcResultMatchers.status().is5xxServerError())
          .andReturn();

      String responseJson = mvcResult.getResponse().getContentAsString();

      // assert
      assertEquals("Could not create task with description=Clean my car", responseJson);
   }
}