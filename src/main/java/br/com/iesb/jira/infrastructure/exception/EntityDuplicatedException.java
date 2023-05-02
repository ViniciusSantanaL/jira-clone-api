package br.com.iesb.jira.infrastructure.exception;

public class EntityDuplicatedException extends RuntimeException {
    public EntityDuplicatedException(String entityName, String fieldType) {
        super(entityName + " -> [" + fieldType + "]: " +
                "Exist Entity with this value!");
    }
}
