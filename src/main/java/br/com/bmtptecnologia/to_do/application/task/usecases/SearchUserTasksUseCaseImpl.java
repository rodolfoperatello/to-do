package br.com.bmtptecnologia.to_do.application.task.usecases;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchUserTasksDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;
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
   public List<TaskDTO> execute(SearchUserTasksDTO searchUserTasksDTO) {
      try {
         // TODO: Criar um método para passar os parametros para o DAO
         // TODO: Fazer a resposta ser paginada
         List<Task> foundTasks = this.taskDAO.findAll();

         return null;
      } catch (final Exception e) {
         logger.error("status=could-not-find-tasks, searchParams={}, exceptionName={}, " +
                 "exceptionMessage={}", searchUserTasksDTO.toString(), e.getClass().getSimpleName(),
             e.getMessage());

         throw e;
      }
   }
}
