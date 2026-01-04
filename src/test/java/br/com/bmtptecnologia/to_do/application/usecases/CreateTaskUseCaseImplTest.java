package br.com.bmtptecnologia.to_do.application.usecases;

import static br.com.bmtptecnologia.to_do.templates.task.CreateTaskDTOTemplate.buildDefaultCreateTaskDTO;
import static br.com.bmtptecnologia.to_do.templates.task.TaskTemplate.buildTaskWithAllGenericFields;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bmtptecnologia.to_do.application.task.dto.CreateTaskDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;
import br.com.bmtptecnologia.to_do.application.task.exception.CouldNotCreateTaskException;
import br.com.bmtptecnologia.to_do.application.task.usecases.CreateTaskUseCaseImpl;
import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.task.TaskDAO;

@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseImplTest {
   @InjectMocks
   private CreateTaskUseCaseImpl createTaskUseCase;
   @Mock
   private TaskDAO taskDAO;

   @Test
   public void mustSaveTask() {
      // arrange
      Task taskMock = buildTaskWithAllGenericFields();

      when(this.taskDAO.save(any(Task.class))).thenReturn(taskMock);

      CreateTaskDTO createTaskDTO = buildDefaultCreateTaskDTO();

      // act
      TaskDTO taskDTO = assertDoesNotThrow(
          () -> this.createTaskUseCase.execute(createTaskDTO));

      // assert
      assertEquals(taskMock.getTaskId().getId(), taskDTO.getId());
      assertEquals(taskMock.getDescription(), taskDTO.getDescription());
      assertEquals(taskMock.getTaskStatus().name(), taskDTO.getTaskStatus());
      assertEquals(taskMock.getCreatedAt(), taskDTO.getCreatedAt());

      verify(this.taskDAO, times(1)).findUserTaskByDescription(
          anyLong(), anyString());
      verify(this.taskDAO, times(1)).save(any(Task.class));
   }

   @Test
   public void mustNotSaveTaskWhenTaskAlreadyExists() {
      // arrange
      Task taskMock = buildTaskWithAllGenericFields();

      when(this.taskDAO.findUserTaskByDescription(anyLong(), anyString()))
          .thenReturn(Optional.of(taskMock));

      CreateTaskDTO generateTaskDTO = buildDefaultCreateTaskDTO();

      // act
      TaskDTO taskDTO = assertDoesNotThrow(
          () -> this.createTaskUseCase.execute(generateTaskDTO));

      // assert
      assertEquals(taskMock.getTaskId().getId(), taskDTO.getId());
      assertEquals(taskMock.getDescription(), taskDTO.getDescription());
      assertEquals(taskMock.getTaskStatus().name(), taskDTO.getTaskStatus());
      assertEquals(taskMock.getCreatedAt(), taskDTO.getCreatedAt());

      verify(this.taskDAO, times(1)).findUserTaskByDescription(
          anyLong(), anyString());
      verify(this.taskDAO, times(0)).save(any(Task.class));
   }

   @Test
   public void mustNotSaveTaskAndThrowsCouldNotGenerateTaskException() {
      // arrange
      CreateTaskDTO generateTaskDTO = buildDefaultCreateTaskDTO();

      when(this.taskDAO.findUserTaskByDescription(anyLong(), anyString()))
          .thenThrow(NullPointerException.class);

      // act
      assertThrows(CouldNotCreateTaskException.class,
          () -> this.createTaskUseCase.execute(generateTaskDTO));

      // assert
      verify(this.taskDAO, times(1)).findUserTaskByDescription(
          anyLong(), anyString());
      verify(this.taskDAO, times(0)).save(any(Task.class));
   }
}
