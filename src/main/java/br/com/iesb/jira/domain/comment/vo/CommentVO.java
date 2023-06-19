package br.com.iesb.jira.domain.comment.vo;

import java.util.UUID;

public record CommentVO(UUID commentId,
                        String commentDescription,
                        UUID taskId,
                        CommentUserVO commentUser) {
}
