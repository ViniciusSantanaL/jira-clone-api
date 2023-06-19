package br.com.iesb.jira.domain.comment.vo;

import java.util.UUID;

public record CommentUserVO(UUID userId, String userUsername) {
}
