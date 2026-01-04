package br.com.bmtptecnologia.to_do.application.task.usecases;

import br.com.bmtptecnologia.to_do.application.task.dto.CreateTaskDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;

public interface CreateTaskUseCase {
    TaskDTO execute(CreateTaskDTO createTaskDTO);
}
