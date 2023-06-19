package br.com.iesb.jira.application.incoming.comment.controller;

import br.com.iesb.jira.application.incoming.comment.commons.converter.CommentConverter;
import br.com.iesb.jira.application.incoming.comment.commons.request.CommentRequest;
import br.com.iesb.jira.application.incoming.comment.commons.response.CommentResponse;
import br.com.iesb.jira.application.incoming.comment.controller.api.CommentUpdateApi;
import br.com.iesb.jira.domain.comment.service.CommentUpdateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api/v1/comments")
@RestController
public class CommentUpdateController implements CommentUpdateApi {

    private final CommentUpdateService commentUpdateService;

    public CommentUpdateController(CommentUpdateService commentUpdateService) {
        this.commentUpdateService = commentUpdateService;
    }

    @Override
    public CommentResponse updateCommentById(final UUID commentId, final CommentRequest commentRequest) {
        return CommentConverter.toResponse(commentUpdateService.updateCommentById(CommentConverter.of(commentId, commentRequest)));
    }
}
