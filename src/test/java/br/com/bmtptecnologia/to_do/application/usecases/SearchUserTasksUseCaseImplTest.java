package br.com.bmtptecnologia.to_do.application.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksFilter;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;
import br.com.bmtptecnologia.to_do.application.task.exception.CouldNotFindTasksException;
import br.com.bmtptecnologia.to_do.application.task.usecases.SearchUserTasksUseCaseImpl;
import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.task.TaskDAO;
import br.com.bmtptecnologia.to_do.templates.task.SearchTasksDTOTemplate;
import br.com.bmtptecnologia.to_do.templates.task.TaskDTOTemplate;
import br.com.bmtptecnologia.to_do.templates.task.TaskTemplate;

@ExtendWith(MockitoExtension.class)
public class SearchUserTasksUseCaseImplTest {
   @InjectMocks
   private SearchUserTasksUseCaseImpl searchUserTasksUseCase;
   @Mock
   private TaskDAO taskDAO;
   private static final String EXCEPTION_MESSAGE = "Could not find tasks with params=%s";

   @Test
   public void mustFindTasks() {
      // arrange
      SearchTasksDTO searchTasksDTOMock = SearchTasksDTOTemplate.buildDefaultSearchTasksDTO();

      TaskDTO taskDTOMock = TaskDTOTemplate.buildDefaultCreatedTaskDTO();

      Task taskMock = TaskTemplate.buildTaskWithAllGenericFields();

      when(taskDAO.findTasksByFilter(any(SearchTasksFilter.class))).thenReturn(List.of(taskMock));

      // act
      List<TaskDTO> foundTasks = this.searchUserTasksUseCase.execute(searchTasksDTOMock);

      // assert
      assertEquals(1, foundTasks.size());

      TaskDTO firstTask = foundTasks.getFirst();

      assertEquals(taskDTOMock.getId(), firstTask.getId());
      assertEquals(taskDTOMock.getDescription(), firstTask.getDescription());
      assertEquals(taskDTOMock.getTaskStatus(), firstTask.getTaskStatus());
      assertEquals(taskDTOMock.getCreatedAt().toLocalDate(),
          firstTask.getCreatedAt().toLocalDate());

      verify(taskDAO, Mockito.times(1))
          .findTasksByFilter(any(SearchTasksFilter.class));
   }

   @Test
   public void mustNotFindTasksAndThrowsCouldNotFindTasksException() {
      // arrange
      SearchTasksDTO searchTasksDTOMock = SearchTasksDTOTemplate.buildDefaultSearchTasksDTO();

      doThrow(NullPointerException.class).when(this.taskDAO)
          .findTasksByFilter(any(SearchTasksFilter.class));

      // act
      CouldNotFindTasksException couldNotFindTasksException = assertThrows(
          CouldNotFindTasksException.class, () ->
              this.searchUserTasksUseCase.execute(searchTasksDTOMock));

      // assert
      assertEquals(String.format(EXCEPTION_MESSAGE, searchTasksDTOMock),
          couldNotFindTasksException.getMessage());
   }
}
