package br.com.bmtptecnologia.to_do.domain.task;

import java.util.List;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksFilter;

public interface TaskDAO {
    Task save(Task Task);

    List<Task> findTasksByFilter(SearchTasksFilter searchTasksFilter);
}
