package br.com.iesb.jira.domain.comment.service;

import br.com.iesb.jira.domain.comment.model.Comment;
import br.com.iesb.jira.domain.comment.repository.CommentRepository;
import br.com.iesb.jira.domain.comment.vo.CommentVO;
import br.com.iesb.jira.domain.comment.vo.builder.CommentBuilder;
import br.com.iesb.jira.domain.task.repository.TaskRepository;
import br.com.iesb.jira.domain.user.repository.UserRepository;
import br.com.iesb.jira.infrastructure.exception.EntityDuplicatedException;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CommentUpdateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentUpdateService.class);

    private final CommentRepository commentRepository;

    public CommentUpdateService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentVO updateCommentById(final CommentVO commentVO) {
        Comment commentToSave = commentRepository.findById(commentVO.commentId())
                .orElseThrow(() -> new EntityNotFoundException("comment", "comment_id"));

        commentToSave.setCommentDescription(commentVO.commentDescription());

        Comment commentSaved = saveComment(commentToSave);

        return CommentBuilder.createCommentVO(commentSaved);
    }

    private Comment saveComment(Comment comment) {
        try {
            return commentRepository.save(comment);
        } catch(DataIntegrityViolationException ex) {
            LOGGER.error("Error to updateComment, {}",ex.getMessage());
            throw new EntityDuplicatedException("comment", "comment_id");
        }
    }
}
