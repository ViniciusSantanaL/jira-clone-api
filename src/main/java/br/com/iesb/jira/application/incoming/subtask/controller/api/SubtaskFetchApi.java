package br.com.iesb.jira.application.incoming.subtask.controller.api;

import br.com.iesb.jira.application.incoming.subtask.commons.response.SubtaskResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "subtasks")
public interface SubtaskFetchApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<SubtaskResponse> fetchAllSubtask(final Pageable pageable);

    @GetMapping("/{subtask_id}")
    @ResponseStatus(HttpStatus.OK)
    SubtaskResponse fetchSubtaskById(@PathVariable(value = "subtask_id") final UUID subtaskId);

}