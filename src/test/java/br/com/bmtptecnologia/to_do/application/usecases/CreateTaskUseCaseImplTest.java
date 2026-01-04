package br.com.bmtptecnologia.to_do.application.usecases;

import static br.com.bmtptecnologia.to_do.templates.task.CreateTaskDTOTemplate.buildDefaultCreateTaskDTO;
import static br.com.bmtptecnologia.to_do.templates.task.TaskTemplate.buildTaskWithAllGenericFields;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bmtptecnologia.to_do.application.task.dto.CreateTaskDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;
import br.com.bmtptecnologia.to_do.application.task.exception.CouldNotCreateTaskException;
import br.com.bmtptecnologia.to_do.application.task.usecases.CreateTaskUseCaseImpl;
import br.com.bmtptecnologia.to_do.application.task.usecases.SearchUserTasksUseCase;
import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.task.TaskDAO;
import br.com.bmtptecnologia.to_do.templates.task.TaskDTOTemplate;

@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseImplTest {
   @InjectMocks
   private CreateTaskUseCaseImpl createTaskUseCase;
   @Mock
   private SearchUserTasksUseCase searchUserTasksUseCase;
   @Mock
   private TaskDAO taskDAO;
   private static final String ERROR_MESSAGE = "Could not create task with description=%s";

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

      verify(this.searchUserTasksUseCase, times(1))
          .execute(any(SearchTasksDTO.class));
      verify(this.taskDAO, times(1)).save(any(Task.class));
   }

   @Test
   public void mustNotSaveTaskWhenTaskAlreadyExists() {
      // arrange
      TaskDTO taskMock = TaskDTOTemplate.buildDefaultCreatedTaskDTO();

      when(this.searchUserTasksUseCase.execute(any(SearchTasksDTO.class)))
          .thenReturn(List.of(taskMock));

      CreateTaskDTO generateTaskDTO = buildDefaultCreateTaskDTO();

      // act
      TaskDTO taskDTO = assertDoesNotThrow(
          () -> this.createTaskUseCase.execute(generateTaskDTO));

      // assert
      assertEquals(taskMock.getId(), taskDTO.getId());
      assertEquals(taskMock.getDescription(), taskDTO.getDescription());
      assertEquals(taskMock.getTaskStatus(), taskDTO.getTaskStatus());
      assertEquals(taskMock.getCreatedAt(), taskDTO.getCreatedAt());

      verify(this.searchUserTasksUseCase, times(1))
          .execute(any(SearchTasksDTO.class));
      verify(this.taskDAO, times(0)).save(any(Task.class));
   }

   @Test
   public void mustNotSaveTaskAndThrowsCouldNotGenerateTaskException() {
      // arrange
      CreateTaskDTO generateTaskDTO = buildDefaultCreateTaskDTO();

      when(this.searchUserTasksUseCase.execute(any(SearchTasksDTO.class)))
          .thenThrow(NullPointerException.class);

      // act // assert
      CouldNotCreateTaskException couldNotCreateTaskException = assertThrows(
          CouldNotCreateTaskException.class,
          () -> this.createTaskUseCase.execute(generateTaskDTO));

      assertEquals(String.format(ERROR_MESSAGE, generateTaskDTO.getDescription()),
          couldNotCreateTaskException.getMessage());

      verify(this.searchUserTasksUseCase, times(1))
          .execute(any(SearchTasksDTO.class));
      verify(this.taskDAO, times(0)).save(any(Task.class));
   }
}
