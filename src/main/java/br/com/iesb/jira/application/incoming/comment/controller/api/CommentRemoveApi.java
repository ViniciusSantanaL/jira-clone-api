package br.com.iesb.jira.application.incoming.comment.controller.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "comments")
public interface CommentRemoveApi {

    @DeleteMapping("/{comment_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCommentById(@PathVariable(name = "comment_id") final UUID commentId);
}