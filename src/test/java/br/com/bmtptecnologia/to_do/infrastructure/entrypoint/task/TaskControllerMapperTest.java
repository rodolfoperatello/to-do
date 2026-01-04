package br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task;

import static br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.TaskControllerMapper.toCreateTaskDTO;
import static br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.TaskControllerMapper.toCreatedTaskResponse;
import static br.com.bmtptecnologia.to_do.templates.task.CreateTaskRequestTemplate.buildDefaultCreateTaskRequest;
import static br.com.bmtptecnologia.to_do.templates.task.CreatedTaskDTOTemplate.buildDefaultCreatedTaskDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.bmtptecnologia.to_do.application.task.dto.CreateTaskDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;
import br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.request.CreateTaskRequest;
import br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.response.CreatedTaskResponse;

class TaskControllerMapperTest {

   @Test
   public void mustConvertFromCreateTaskRequestToCreateTaskDTO() {
      // arrange
      CreateTaskRequest createTaskRequestMock = buildDefaultCreateTaskRequest();

      // act
      CreateTaskDTO createTaskDTO = toCreateTaskDTO(createTaskRequestMock);

      // assert
      assertEquals(createTaskRequestMock.getUserId(), createTaskDTO.getUserId());
      assertEquals(createTaskRequestMock.getDescription(), createTaskDTO.getDescription());
   }

   @Test
   public void mustConvertFromCreatedTaskDTOToCreatedTaskResponse() {
      // arrange
      TaskDTO taskDTOMock = buildDefaultCreatedTaskDTO();

      // act
      CreatedTaskResponse createdTaskResponse = toCreatedTaskResponse(
          taskDTOMock);

      // assert
      assertEquals(taskDTOMock.getId(), createdTaskResponse.getTaskId());
      assertEquals(taskDTOMock.getDescription(), createdTaskResponse.getDescription());
      assertEquals(taskDTOMock.getTaskStatus(), createdTaskResponse.getTaskStatus());
      assertEquals(taskDTOMock.getCreatedAt(), createdTaskResponse.getCreatedAt());
   }
}