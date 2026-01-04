package br.com.bmtptecnologia.to_do.templates.task;

import br.com.bmtptecnologia.to_do.application.task.dto.CreateTaskDTO;

public class CreateTaskDTOTemplate {
   private static final Long FIRST_USER_ID = 1L;
   private static final String DESCRIPTION = "Clean my car";

   public static CreateTaskDTO buildDefaultCreateTaskDTO() {
      return new CreateTaskDTO(FIRST_USER_ID, DESCRIPTION);
   }
}
