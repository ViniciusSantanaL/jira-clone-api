package br.com.iesb.jira.domain.comment.service;

import br.com.iesb.jira.domain.comment.model.Comment;
import br.com.iesb.jira.domain.comment.repository.CommentRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentFetchService {

    private final CommentRepository commentRepository;

    public CommentFetchService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Page<Comment> fetchAllComment(final Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public Comment fetchCommentById(final UUID commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() ->  new EntityNotFoundException("comment", "comment_id"));
    }
}
