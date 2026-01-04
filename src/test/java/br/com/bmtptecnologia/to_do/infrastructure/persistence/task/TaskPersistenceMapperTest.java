package br.com.bmtptecnologia.to_do.infrastructure.persistence.task;

import static br.com.bmtptecnologia.to_do.infrastructure.persistence.task.TaskPersistenceMapper.toDomain;
import static br.com.bmtptecnologia.to_do.infrastructure.persistence.task.TaskPersistenceMapper.toEntity;
import static br.com.bmtptecnologia.to_do.templates.task.TaskEntityTemplate.buildTaskEntityWithAllGenericFields;
import static br.com.bmtptecnologia.to_do.templates.task.TaskTemplate.buildTaskByDescriptionAndUserId;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.user.UserId;

class TaskPersistenceMapperTest {
   private static final String TASK_DESCRIPTION = "Clean my car";
   private static final UserId USER_ID = UserId.create(1L);

   @Test
   public void mustConvertToDomain() {
      // arrange
      TaskEntity taskEntityMock = buildTaskEntityWithAllGenericFields();

      // act
      Task task = toDomain((taskEntityMock));

      // assert
      assertNotNull(task);
      assertEquals(taskEntityMock.getId(), task.getTaskId().getId());
      assertEquals(taskEntityMock.getUserId(), task.getTaskId().getId());
      assertEquals(taskEntityMock.getDescription(), task.getDescription());
      assertEquals(taskEntityMock.getTaskStatus(), task.getTaskStatus().name());
      assertEquals(taskEntityMock.getCreatedAt(), task.getCreatedAt());
   }

   @Test
   public void mustConvertToEntity() {
      // arrange
      Task taskMock = buildTaskByDescriptionAndUserId(USER_ID, TASK_DESCRIPTION);

      // act
      TaskEntity taskEntity = toEntity(taskMock);

      // assert
      assertNotNull(taskEntity);
      assertEquals(taskMock.getUserId().getId(), taskEntity.getUserId());
      assertEquals(taskMock.getDescription(), taskEntity.getDescription());
      assertEquals(taskMock.getTaskStatus().name(), taskEntity.getTaskStatus());
   }
}
