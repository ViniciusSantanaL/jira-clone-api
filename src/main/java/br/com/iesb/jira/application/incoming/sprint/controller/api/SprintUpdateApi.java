package br.com.iesb.jira.application.incoming.sprint.controller.api;

import br.com.iesb.jira.application.incoming.sprint.commons.request.SprintRequest;
import br.com.iesb.jira.application.incoming.sprint.commons.response.SprintResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "sprints")
public interface SprintUpdateApi {

    @PutMapping("/{sprint_id}")
    @ResponseStatus(HttpStatus.OK)
    SprintResponse updateSprintById(@PathVariable(name = "sprint_id") final UUID sprintId,
                                     @RequestBody @Valid final SprintRequest sprintRequest);

}
