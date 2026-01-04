package br.com.bmtptecnologia.to_do.application.task.exception;

public class CouldNotCreateTaskException extends RuntimeException {

    public CouldNotCreateTaskException(String description, Throwable throwable) {
        super(String.format("Could not create task with description=%s", description), throwable);
    }
}
