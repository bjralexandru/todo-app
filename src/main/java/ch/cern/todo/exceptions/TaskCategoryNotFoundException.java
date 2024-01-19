package ch.cern.todo.exceptions;

public class TaskCategoryNotFoundException extends RuntimeException {

    private String errorMessage;

    public TaskCategoryNotFoundException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}