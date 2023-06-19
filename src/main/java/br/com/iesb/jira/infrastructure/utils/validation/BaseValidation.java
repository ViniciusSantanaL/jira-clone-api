package br.com.iesb.jira.infrastructure.utils.validation;

import java.util.UUID;

public abstract class BaseValidation<T> {

    private UUID id;

    public UUID getId() {
        return id;
    }
}
