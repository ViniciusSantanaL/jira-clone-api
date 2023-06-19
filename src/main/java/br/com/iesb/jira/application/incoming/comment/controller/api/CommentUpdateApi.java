package br.com.iesb.jira.application.incoming.comment.controller.api;

import br.com.iesb.jira.application.incoming.comment.commons.request.CommentRequest;
import br.com.iesb.jira.application.incoming.comment.commons.response.CommentResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "comments")
public interface CommentUpdateApi {

    @PutMapping("/{comment_id}")
    @ResponseStatus(HttpStatus.OK)
    CommentResponse updateCommentById(@PathVariable(name = "comment_id") final UUID commentId,
                                     @RequestBody @Valid final CommentRequest commentRequest);

}
