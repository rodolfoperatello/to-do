package br.com.bmtptecnologia.to_do.application.task.exception;

import br.com.bmtptecnologia.to_do.application.task.dto.SearchTasksDTO;

public class CouldNotFindTasksException extends RuntimeException {

    public CouldNotFindTasksException(SearchTasksDTO searchTasksDTO, Throwable throwable) {
        super(String.format("Could not find tasks with params=%s", searchTasksDTO), throwable);
    }
}
