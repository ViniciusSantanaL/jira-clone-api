package br.com.iesb.jira.application.incoming.sprint.controller.api;

import br.com.iesb.jira.application.incoming.sprint.commons.request.SprintRequest;
import br.com.iesb.jira.application.incoming.sprint.commons.response.SprintResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "sprints")
public interface SprintCreateApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    SprintResponse createSprint(final @RequestBody @Valid SprintRequest request);

}
