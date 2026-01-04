package br.com.bmtptecnologia.to_do.application.task.mapper;

import java.util.List;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchUserTasksDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;
import br.com.bmtptecnologia.to_do.domain.task.Task;

public class TaskApplicationMapper {

   public static TaskDTO toCreatedTaskDTO(Task task) {
      return new TaskDTO(task.getTaskId().getId(), task.getDescription(),
          task.getTaskStatus().name(), task.getCreatedAt());
   }

//   public static SearchUserTasksDTO toSearchUserTasksDTO(List<Task> tasks) {
//      List<TaskDTO> tasksDTOs = tasks.stream()
//          .map(TaskApplicationMapper::toCreatedTaskDTO).toList();
//
//      return new SearchUserTasksDTO(tasksDTOs);
//   }
}
