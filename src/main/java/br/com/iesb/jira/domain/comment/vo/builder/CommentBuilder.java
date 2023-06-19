package br.com.iesb.jira.domain.comment.vo.builder;

import br.com.iesb.jira.domain.comment.model.Comment;
import br.com.iesb.jira.domain.comment.vo.CommentUserVO;
import br.com.iesb.jira.domain.comment.vo.CommentVO;
import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.sprint.vo.SprintTaskVO;
import br.com.iesb.jira.domain.sprint.vo.SprintVO;
import br.com.iesb.jira.domain.task.model.Task;
import br.com.iesb.jira.domain.user.model.User;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class CommentBuilder {

    private CommentBuilder() {
        throw new UnsupportedOperationException("cannot be call constructor");
    }

    public static Comment create(CommentVO commentVO, Task task, User user) {
        Comment comment = new Comment();
        comment.setCommentDescription(commentVO.commentDescription());
        comment.setTask(task);
        comment.setUser(user);
        return comment;
    }

    public static CommentVO createCommentVO(Comment comment) {
        return new CommentVO(
                comment.getId(),
                comment.getCommentDescription(),
                comment.getTask().getId(),
                new CommentUserVO(comment.getUser().getId(), comment.getUser().getUserUsername()));
    }

}
