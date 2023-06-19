package br.com.iesb.jira.application.incoming.task.controller.api;

import br.com.iesb.jira.application.incoming.task.commons.request.TaskRequest;
import br.com.iesb.jira.application.incoming.task.commons.response.TaskResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "tasks")
public interface TaskUpdateApi {

    @PutMapping("/{task_id}")
    @ResponseStatus(HttpStatus.OK)
    TaskResponse updateTaskById(@PathVariable(name = "task_id") final UUID taskId,
                                  @RequestBody @Valid final TaskRequest taskRequest);

}
