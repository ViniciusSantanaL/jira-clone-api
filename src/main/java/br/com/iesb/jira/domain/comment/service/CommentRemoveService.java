package br.com.iesb.jira.domain.comment.service;

import br.com.iesb.jira.domain.comment.model.Comment;
import br.com.iesb.jira.domain.comment.repository.CommentRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentRemoveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentRemoveService.class);

    private final CommentRepository commentRepository;

    public CommentRemoveService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void deleteCommentById(final UUID commentId) {
        LOGGER.info("deleteCommentById");
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("comment", "comment_id"));
        commentRepository.deleteById(comment.getId());
        LOGGER.info("comment delete by id: {}",commentId);
    }

}
