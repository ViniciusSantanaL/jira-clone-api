package br.com.iesb.jira.infrastructure.errorhandling;

public record MessageResponse(String message, int httpStatusCode){ }
