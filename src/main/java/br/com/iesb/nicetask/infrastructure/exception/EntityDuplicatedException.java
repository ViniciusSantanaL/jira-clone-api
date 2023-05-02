package br.com.iesb.nicetask.infrastructure.exception;

public class EntityDuplicatedException extends RuntimeException {
    public EntityDuplicatedException(String entityName, String fieldName, String fieldValue) {

        super(entityName + " -> [" + fieldName + "="+ fieldValue + "]: " +
                "Exist Entity with this value!");
    }
}
