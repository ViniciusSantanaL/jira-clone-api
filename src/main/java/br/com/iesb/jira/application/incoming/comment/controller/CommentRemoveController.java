package br.com.iesb.jira.application.incoming.comment.controller;

import br.com.iesb.jira.application.incoming.comment.controller.api.CommentRemoveApi;
import br.com.iesb.jira.domain.comment.service.CommentRemoveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentRemoveController implements CommentRemoveApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentRemoveController.class);
    private final CommentRemoveService commentRemoveService;

    public CommentRemoveController(CommentRemoveService commentRemoveService) {
        this.commentRemoveService = commentRemoveService;
    }

    @Override
    public void deleteCommentById(final UUID commentId) {
        LOGGER.info("deleteCommentById, commentId: {}", commentId);
        commentRemoveService.deleteCommentById(commentId);
    }
}
