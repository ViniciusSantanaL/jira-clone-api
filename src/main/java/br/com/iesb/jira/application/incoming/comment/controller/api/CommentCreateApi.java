package br.com.iesb.jira.application.incoming.comment.controller.api;

import br.com.iesb.jira.application.incoming.comment.commons.request.CommentRequest;
import br.com.iesb.jira.application.incoming.comment.commons.response.CommentResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "comments")
public interface CommentCreateApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CommentResponse createComment(final @RequestBody @Valid CommentRequest request);

}
