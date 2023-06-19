package br.com.iesb.jira.application.incoming.comment.commons.converter;

import br.com.iesb.jira.application.incoming.comment.commons.request.CommentRequest;
import br.com.iesb.jira.application.incoming.comment.commons.response.CommentResponse;
import br.com.iesb.jira.application.incoming.comment.commons.response.CommentUserResponse;
import br.com.iesb.jira.domain.comment.model.Comment;
import br.com.iesb.jira.domain.comment.vo.CommentUserVO;
import br.com.iesb.jira.domain.comment.vo.CommentVO;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CommentConverter {

    private CommentConverter() {
        throw new UnsupportedOperationException("can not be call constructor");
    }

    public static CommentVO of(final CommentRequest request) {
        return new CommentVO(null,
                request.commentDescription(),
                request.taskId(),
                new CommentUserVO(request.userId(), null));
    }

    public static CommentVO of(final UUID commentId, final CommentRequest request) {
            return new CommentVO(commentId,
                    request.commentDescription(),
                    request.taskId(),
                    new CommentUserVO(request.userId(), null));
    }

    public static CommentResponse toResponse(CommentVO sprintVO) {
        return new CommentResponse(sprintVO.commentId(),
                sprintVO.commentDescription(),
                new CommentUserResponse(sprintVO.commentUser().userId(), sprintVO.commentUser().userUsername()),
                sprintVO.taskId());
    }

    public static CommentResponse toResponse(Comment comment) {
        return new CommentResponse(comment.getId(),
                comment.getCommentDescription(),
                new CommentUserResponse(comment.getUser().getId(), comment.getUser().getUsername()),
                comment.getTask().getId());
    }
}
