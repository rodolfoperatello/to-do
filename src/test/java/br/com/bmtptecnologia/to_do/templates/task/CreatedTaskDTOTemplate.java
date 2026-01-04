package br.com.bmtptecnologia.to_do.templates.task;

import java.time.LocalDateTime;

import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;

public class CreatedTaskDTOTemplate {
   private static final Long FIRST_TASK_ID = 1L;
   private static final String TASK_DESCRIPTION = "Clean my car";
   private static final String CREATED_TASK_STATUS = "CREATED";
   private static final LocalDateTime LOCAL_DATE_TIME_NOW = LocalDateTime.now();

   public static TaskDTO buildDefaultCreatedTaskDTO() {
      return new TaskDTO(FIRST_TASK_ID, TASK_DESCRIPTION, CREATED_TASK_STATUS,
          LOCAL_DATE_TIME_NOW);
   }
}
