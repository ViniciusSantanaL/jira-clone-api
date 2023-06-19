package br.com.iesb.jira.application.incoming.subtask.controller.api;

import br.com.iesb.jira.application.incoming.subtask.commons.request.SubtaskRequest;
import br.com.iesb.jira.application.incoming.subtask.commons.response.SubtaskResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "subtasks")
public interface SubtaskCreateApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SubtaskResponse createSubtask(final @RequestBody @Valid SubtaskRequest request);

}
