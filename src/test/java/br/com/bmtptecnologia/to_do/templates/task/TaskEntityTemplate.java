package br.com.bmtptecnologia.to_do.templates.task;

import java.time.LocalDateTime;

import org.springframework.test.util.ReflectionTestUtils;

import br.com.bmtptecnologia.to_do.infrastructure.persistence.task.TaskEntity;

public class TaskEntityTemplate {
   private static final Long FIRST_USER_ID = 1L;
   private static final String TASK_DESCRIPTION = "Clean my car";
   private static final String CREATED_TASK_STATUS = "CREATED";
   private static final LocalDateTime LOCAL_DATE_TIME_NOW = LocalDateTime.now();

   public static TaskEntity buildTaskEntityWithAllGenericFields() {
      TaskEntity taskEntityMock = new TaskEntity(FIRST_USER_ID, TASK_DESCRIPTION,
          CREATED_TASK_STATUS);

      ReflectionTestUtils.setField(taskEntityMock, "id", 1L);
      ReflectionTestUtils.setField(taskEntityMock, "createdAt", LOCAL_DATE_TIME_NOW);
      ReflectionTestUtils.setField(taskEntityMock, "updatedAt", LOCAL_DATE_TIME_NOW);

      return taskEntityMock;
   }
}
