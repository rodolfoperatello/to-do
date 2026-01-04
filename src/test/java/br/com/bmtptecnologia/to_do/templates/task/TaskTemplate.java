package br.com.bmtptecnologia.to_do.templates.task;

import static br.com.bmtptecnologia.to_do.domain.task.TaskStatus.CREATED;

import java.time.LocalDateTime;

import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.task.TaskStatus;
import br.com.bmtptecnologia.to_do.domain.user.UserId;

public class TaskTemplate {
   private static final Long FIRST_TASK_ID = 1L;
   private static final Long FIRST_USER_ID = 1L;
   private static final String TASK_DESCRIPTION = "Clean my car";
   private static final TaskStatus TASK_STATUS = CREATED;
   private static final LocalDateTime LOCAL_DATE_TIME_NOW = LocalDateTime.now();

   public static Task buildTaskByDescriptionAndUserId(UserId userId, String description) {
      return Task.create(userId, description);
   }

   public static Task buildTaskWithAllGenericFields() {
      return Task.recreate(FIRST_TASK_ID, FIRST_USER_ID, TASK_DESCRIPTION, TASK_STATUS,
          LOCAL_DATE_TIME_NOW);
   }
}
