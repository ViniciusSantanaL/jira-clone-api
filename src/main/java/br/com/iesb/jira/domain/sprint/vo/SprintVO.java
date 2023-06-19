package br.com.iesb.jira.domain.sprint.vo;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record SprintVO(UUID id,
                       Integer sprintCode,
                       LocalDate sprintStartDate,
                       LocalDate sprintEndDate,
                       UUID sprintProjectId,
                       Set<SprintTaskVO> sprintTasksVO) {
    public SprintVO(LocalDate sprintStartDate, LocalDate sprintEndDate, UUID sprintProjectId) {
        this(null, null, sprintStartDate, sprintEndDate, sprintProjectId, null);
    }

    public SprintVO(UUID sprintId,LocalDate sprintStartDate, LocalDate sprintEndDate,
                    UUID sprintProjectId, Set<SprintTaskVO> sprintTasksVO) {
        this(sprintId, null, sprintStartDate, sprintEndDate, sprintProjectId, sprintTasksVO);
    }
}
