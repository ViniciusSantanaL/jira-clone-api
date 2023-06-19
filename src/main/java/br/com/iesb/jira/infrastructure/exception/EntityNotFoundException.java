package br.com.iesb.jira.infrastructure.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName,String message) {
        super(entityName + " -> [" + message + "]: " +
                "Not Found Entity with this value!");
    }
}
