package br.com.iesb.jira.domain.sprint.vo;

import java.util.UUID;

public record SprintTaskVO(UUID taskId, Integer taskCode, String taskTitle) {

    public SprintTaskVO(UUID taskId) {
        this(taskId, null, null);
    }
}
