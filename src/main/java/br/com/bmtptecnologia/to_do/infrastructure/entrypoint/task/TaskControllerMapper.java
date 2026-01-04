package br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task;

import br.com.bmtptecnologia.to_do.application.task.dto.CreateTaskDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;
import br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.request.CreateTaskRequest;
import br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.response.CreatedTaskResponse;

public class TaskControllerMapper {

   public static CreateTaskDTO toCreateTaskDTO(CreateTaskRequest createTaskRequest) {
      return new CreateTaskDTO(createTaskRequest.getUserId(), createTaskRequest.getDescription());
   }

   public static CreatedTaskResponse toCreatedTaskResponse(TaskDTO taskDTO) {
      return new CreatedTaskResponse(taskDTO.getId(), taskDTO.getDescription(),
          taskDTO.getTaskStatus(), taskDTO.getCreatedAt(),
          taskDTO.getCreatedAt());
   }
}
