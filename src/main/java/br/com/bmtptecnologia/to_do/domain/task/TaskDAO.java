package br.com.bmtptecnologia.to_do.domain.task;

import java.util.List;
import java.util.Optional;

public interface TaskDAO {
    Task save(Task Task);

    Optional<Task> findUserTaskByDescription(Long userId, String description);

    List<Task> findAll();
}
