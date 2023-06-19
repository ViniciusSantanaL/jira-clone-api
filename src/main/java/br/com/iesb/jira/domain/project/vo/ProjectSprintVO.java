package br.com.iesb.jira.domain.project.vo;

import java.util.UUID;

public record ProjectSprintVO(UUID sprintId, Integer projectCode) {
    public ProjectSprintVO(UUID sprintId) {
        this(sprintId, null);
    }
}
