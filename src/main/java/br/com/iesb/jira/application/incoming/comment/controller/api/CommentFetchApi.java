package br.com.iesb.jira.application.incoming.comment.controller.api;

import br.com.iesb.jira.application.incoming.comment.commons.response.CommentResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@Tag(name = "comments")
public interface CommentFetchApi {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<CommentResponse> fetchAllComment(final Pageable pageable);

    @GetMapping("/{comment_id}")
    @ResponseStatus(HttpStatus.OK)
    CommentResponse fetchCommentById(@PathVariable(value = "comment_id") final UUID commentId);

}