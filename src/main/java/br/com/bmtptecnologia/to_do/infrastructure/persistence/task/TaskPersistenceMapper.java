package br.com.bmtptecnologia.to_do.infrastructure.persistence.task;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.bmtptecnologia.to_do.domain.task.Task;
import br.com.bmtptecnologia.to_do.domain.task.TaskStatus;

@Component
public class TaskPersistenceMapper {

    public static List<Task> toDomain(List<TaskEntity> taskEntity) {
        return taskEntity.stream().map(TaskPersistenceMapper::toDomain).toList();
    }

    public static Task toDomain(TaskEntity taskEntity) {
        return Task.recreate(taskEntity.getId(), taskEntity.getUserId(), taskEntity.getDescription(),
            TaskStatus.valueOf(taskEntity.getTaskStatus()), taskEntity.getCreatedAt());
    }

    public static TaskEntity toEntity(Task task) {
        return new TaskEntity(task.getUserId().getId(), task.getDescription(),
            task.getTaskStatus().name());
    }
}
