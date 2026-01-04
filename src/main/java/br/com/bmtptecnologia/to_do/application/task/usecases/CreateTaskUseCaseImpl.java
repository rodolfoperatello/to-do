package br.com.bmtptecnologia.to_do.application.task.usecases;

import static br.com.bmtptecnologia.to_do.application.task.mapper.TaskApplicationMapper.toCreatedTaskDTO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.bmtptecnologia.to_do.application.task.dto.CreateTaskDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;
import br.com.bmtptecnologia.to_do.application.task.exception.CouldNotCreateTaskException;
import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.task.TaskDAO;
import br.com.bmtptecnologia.to_do.domain.user.UserId;

@Component
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {
    @Autowired
    private final TaskDAO taskDAO;
    private final SearchUserTasksUseCase searchUserTasksUseCase;
    private static final Logger logger = LoggerFactory.getLogger(CreateTaskUseCaseImpl.class);

    public CreateTaskUseCaseImpl(TaskDAO taskDAO, SearchUserTasksUseCase searchUserTasksUseCase) {
        this.taskDAO = taskDAO;
       this.searchUserTasksUseCase = searchUserTasksUseCase;
    }

    @Override
    public TaskDTO execute(CreateTaskDTO createTaskDTO) {
        try {
            SearchTasksDTO searchTasksDTO = SearchTasksDTO.builder()
                .withUserId(createTaskDTO.getUserId())
                .withDescription(createTaskDTO.getDescription())
                .build();

            List<TaskDTO> foundTasks = this.searchUserTasksUseCase.execute(searchTasksDTO);

            if (!foundTasks.isEmpty()) {
                logger.info("status=task-already-exists, userId={}, description={}",
                    createTaskDTO.getUserId(), createTaskDTO.getDescription());

                return foundTasks.getFirst();
            }

            Task task = Task.create(UserId.create(createTaskDTO.getUserId()),
                createTaskDTO.getDescription());

            return toCreatedTaskDTO(this.taskDAO.save(task));
        } catch (final Exception e) {
            logger.error("status=could-not-create-task, userId={}, description={}, " +
                    "exceptionName={}, exceptionMessage={}", createTaskDTO.getUserId(),
                createTaskDTO.getDescription(), e.getClass().getSimpleName(), e.getMessage());

            throw new CouldNotCreateTaskException(createTaskDTO.getDescription(), e);
        }
    }
}
