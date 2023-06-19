package br.com.iesb.jira.domain.comment.service;

import br.com.iesb.jira.domain.comment.model.Comment;
import br.com.iesb.jira.domain.comment.repository.CommentRepository;
import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.comment.model.Comment;
import br.com.iesb.jira.domain.comment.vo.CommentVO;
import br.com.iesb.jira.domain.comment.vo.builder.CommentBuilder;
import br.com.iesb.jira.domain.task.model.Task;
import br.com.iesb.jira.domain.task.repository.TaskRepository;
import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.domain.user.repository.UserRepository;
import br.com.iesb.jira.infrastructure.exception.BusinessException;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CommentCreateService {
    public static final Logger LOGGER = LoggerFactory.getLogger(CommentCreateService.class);

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    public CommentCreateService(CommentRepository commentRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public CommentVO createComment(CommentVO commentVO) {
        Task task = taskRepository.findById(commentVO.taskId())
                .orElseThrow(() -> new EntityNotFoundException("task", "task_id"));

        User user = userRepository.findById(commentVO.commentUser().userId())
                .orElseThrow(() -> new EntityNotFoundException("user", "user_id"));

        Comment comment = saveComment(CommentBuilder.create(commentVO,task, user));
        LOGGER.info("createComment, Comment: {}", comment);

        return CommentBuilder.createCommentVO(comment);
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