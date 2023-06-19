package br.com.iesb.jira.domain.subtask.vo;

import br.com.iesb.jira.domain.subtask.model.SubtaskStatus;

import java.util.UUID;

public record SubtaskVO(UUID subtaskId,
                        String subtaskTitle,
                        UUID taskId,
                        SubtaskStatus subtaskStatus) {
}
