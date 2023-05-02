package br.com.iesb.jira.infrastructure.exception;

public class NotHaveAccessException extends RuntimeException {

    public NotHaveAccessException(String message) {
        super(message);
    }
}
