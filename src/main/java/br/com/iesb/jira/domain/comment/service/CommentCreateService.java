package br.com.iesb.jira.domain.comment.service;

import br.com.iesb.jira.domain.comment.model.Comment;
import br.com.iesb.jira.domain.comment.repository.CommentRepository;
import br.com.iesb.jira.infrastructure.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CommentCreateService {
    public static final Logger LOGGER = LoggerFactory.getLogger(CommentCreateService.class);

    private final CommentRepository commentRepository;

    public CommentCreateService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Object createComment() {

        return null;
    }

    private Comment saveComment(Comment comment) {
        try {
            return commentRepository.save(comment);
        } catch (DataIntegrityViolationException ex) {
            LOGGER.error("Error to save comment", ex);
            throw new BusinessException("Erro to Save comment");
        }
    }
}