package br.com.bmtptecnologia.to_do.application.task.mapper;

import static br.com.bmtptecnologia.to_do.application.task.mapper.TaskApplicationMapper.toCreatedTaskDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;
import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.templates.task.TaskTemplate;

class TaskApplicationMapperTest {

   @Test
   public void mustConvertFromTaskToCreatedTaskDTO() {
      // arrange
      Task taskMock = TaskTemplate.buildTaskWithAllGenericFields();
      
      // act
      TaskDTO taskDTO = toCreatedTaskDTO(taskMock);

      // assert
      assertEquals(taskMock.getTaskId().getId(), taskDTO.getId());
      assertEquals(taskMock.getDescription(), taskDTO.getDescription());
      assertEquals(taskMock.getTaskStatus().name(), taskDTO.getTaskStatus());
      assertEquals(taskMock.getCreatedAt(), taskDTO.getCreatedAt());
   }
}