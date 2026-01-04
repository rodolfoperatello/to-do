package br.com.bmtptecnologia.to_do.application.task.usecases;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksFilter;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;
import br.com.bmtptecnologia.to_do.application.task.exception.CouldNotFindTasksException;
import br.com.bmtptecnologia.to_do.application.task.mapper.TaskApplicationMapper;
import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.task.TaskDAO;

@Component
public class SearchUserTasksUseCaseImpl implements SearchUserTasksUseCase {
   private final TaskDAO taskDAO;
   private static final Logger logger = LoggerFactory.getLogger(SearchUserTasksUseCaseImpl.class);

   public SearchUserTasksUseCaseImpl(TaskDAO taskDAO) {
      this.taskDAO = taskDAO;
   }

   @Override
   public List<TaskDTO> execute(SearchTasksDTO searchTasksDTO) {
      try {
         SearchTasksFilter searchTasksFilter = SearchTasksFilter.builder()
             .withUserId(searchTasksDTO.getUserId())
             .withDescription(searchTasksDTO.getDescription())
             .withStatus(searchTasksDTO.getStatus())
             .withCreatedAtStart(searchTasksDTO.getCreatedAtStart())
             .withCreatedAtEnd(searchTasksDTO.getCreatedAtEnd())
             .build();

         List<Task> foundTasks = this.taskDAO.findTasksByFilter(searchTasksFilter);

         return foundTasks.stream().map(TaskApplicationMapper::toCreatedTaskDTO).toList();
      } catch (final Exception e) {
         logger.error("status=could-not-find-tasks, searchParams={}, exceptionName={}, " +
                 "exceptionMessage={}", searchTasksDTO, e.getClass().getSimpleName(),
             e.getMessage());

         throw new CouldNotFindTasksException(searchTasksDTO, e);
      }
   }
}
