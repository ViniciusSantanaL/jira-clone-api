package br.com.iesb.jira.application.incoming.comment.controller;

import br.com.iesb.jira.application.incoming.comment.commons.converter.CommentConverter;
import br.com.iesb.jira.application.incoming.comment.commons.request.CommentRequest;
import br.com.iesb.jira.application.incoming.comment.commons.response.CommentResponse;
import br.com.iesb.jira.application.incoming.comment.controller.api.CommentCreateApi;
import br.com.iesb.jira.domain.comment.service.CommentCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/comments")
@RestController
public class CommentCreateController implements CommentCreateApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommentCreateController.class);
    private final CommentCreateService commentCreateService;

    public CommentCreateController(CommentCreateService commentCreateService) {
        this.commentCreateService = commentCreateService;
    }

    @Override
    public CommentResponse createComment(final CommentRequest request) {
        LOGGER.info("start create comment, request: {}", request);
        return CommentConverter.toResponse(commentCreateService.createComment(CommentConverter.of(request)));
    }
}
