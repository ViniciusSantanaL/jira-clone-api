package br.com.iesb.jira.application.incoming.sprint.controller.api;

import br.com.iesb.jira.application.incoming.sprint.commons.response.SprintResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "sprints")
public interface SprintFetchApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<SprintResponse> fetchAllSprint(final Pageable pageable);

    @GetMapping("/{sprint_id}")
    @ResponseStatus(HttpStatus.OK)
    SprintResponse fetchSprintById(@PathVariable(value = "sprint_id") final UUID sprintId);

}