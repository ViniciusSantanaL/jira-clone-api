package br.com.iesb.jira.application.incoming.task.controller.api;

import br.com.iesb.jira.application.incoming.task.commons.request.TaskRequest;
import br.com.iesb.jira.application.incoming.task.commons.response.TaskResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "tasks")
public interface TaskCreateApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TaskResponse createTask(final @RequestBody @Valid TaskRequest request);

}
