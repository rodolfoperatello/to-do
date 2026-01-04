package br.com.bmtptecnologia.to_do.application.task.usecases;

import java.util.List;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchUserTasksDTO;
import br.com.bmtptecnologia.to_do.application.task.dto.TaskDTO;

public interface SearchUserTasksUseCase {
   List<TaskDTO> execute(SearchUserTasksDTO searchUserTasksDTO);
}
