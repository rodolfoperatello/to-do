package br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task;

import static br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.TaskControllerMapper.toCreateTaskDTO;
import static br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.TaskControllerMapper.toCreatedTaskResponse;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bmtptecnologia.to_do.application.task.dto.CreateTaskDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;
import br.com.bmtptecnologia.to_do.application.task.usecases.CreateTaskUseCase;
import br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.request.CreateTaskRequest;
import br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.response.CreatedTaskResponse;

@RestController
@RequestMapping(value = "/api/v1/task")
@Validated
public class TaskController {
   @Autowired
   private CreateTaskUseCase createTaskUseCase;
   private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

   public TaskController(CreateTaskUseCase createTaskUseCase) {
      this.createTaskUseCase = createTaskUseCase;
   }

   @PostMapping
   public ResponseEntity<?> createTask(@Valid @RequestBody CreateTaskRequest createTaskRequest) {
      try {
         CreateTaskDTO createTaskDTO = toCreateTaskDTO(createTaskRequest);

         TaskDTO taskDTO = this.createTaskUseCase.execute(createTaskDTO);

         CreatedTaskResponse createdTaskResponse = toCreatedTaskResponse(taskDTO);

         return new ResponseEntity<>(createdTaskResponse, HttpStatus.CREATED);
      } catch (final Exception e) {
         logger.error("status=could-not-create-task, description={}, exceptionName={}, " +
             "exceptionMessage={}", createTaskRequest.getDescription(),
             e.getClass().getSimpleName(), e.getMessage());

         return new ResponseEntity<>(String.format("Could not create task with description=%s",
             createTaskRequest.getDescription()), HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }
}
