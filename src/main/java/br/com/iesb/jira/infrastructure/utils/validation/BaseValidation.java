package br.com.iesb.jira.infrastructure.utils.validation;

import java.util.UUID;

public abstract class BaseValidation {

    private final UUID id;

    protected BaseValidation(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
