package br.com.bmtptecnologia.to_do.templates.task;

import br.com.bmtptecnologia.to_do.infrastructure.entrypoint.task.request.CreateTaskRequest;

public class CreateTaskRequestTemplate {
   private static final Long FIRST_USER_ID = 1L;
   private static final String DESCRIPTION = "Clean my car";

   public static CreateTaskRequest buildDefaultCreateTaskRequest() {
      return new CreateTaskRequest(FIRST_USER_ID, DESCRIPTION);
   }

   public static CreateTaskRequest buildCreateTaskRequestWithInvalidFields() {
      return new CreateTaskRequest(-1L, " ");
   }
}
