package br.com.iesb.jira.application.incoming.subtask.controller.api;

import br.com.iesb.jira.application.incoming.subtask.commons.request.SubtaskRequest;
import br.com.iesb.jira.application.incoming.subtask.commons.response.SubtaskResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "subtasks")
public interface SubtaskUpdateApi {

    @PutMapping("/{subtask_id}")
    @ResponseStatus(HttpStatus.OK)
    SubtaskResponse updateSubtaskById(@PathVariable(name = "subtask_id") final UUID subtaskId,
                                     @RequestBody @Valid final SubtaskRequest subtaskRequest);

}
