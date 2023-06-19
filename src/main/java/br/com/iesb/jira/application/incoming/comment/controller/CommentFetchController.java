package br.com.iesb.jira.application.incoming.comment.controller;

import br.com.iesb.jira.application.incoming.comment.commons.converter.CommentConverter;
import br.com.iesb.jira.application.incoming.comment.commons.response.CommentResponse;
import br.com.iesb.jira.application.incoming.comment.controller.api.CommentFetchApi;
import br.com.iesb.jira.domain.comment.service.CommentFetchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentFetchController implements CommentFetchApi {

    private final CommentFetchService commentFetchService;

    public CommentFetchController(CommentFetchService commentFetchService) {
        this.commentFetchService = commentFetchService;
    }

    @Override
    public Page<CommentResponse> fetchAllComment(final Pageable pageable) {
        return commentFetchService.fetchAllComment(pageable)
                .map(CommentConverter::toResponse);
    }

    @Override
    public CommentResponse fetchCommentById(final UUID commentId) {
        return CommentConverter.toResponse(commentFetchService.fetchCommentById(commentId));
    }
}
