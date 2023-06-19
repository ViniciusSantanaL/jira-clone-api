package br.com.iesb.jira.application.incoming.sprint.commons.converter;

import br.com.iesb.jira.application.incoming.sprint.commons.request.SprintRequest;
import br.com.iesb.jira.application.incoming.sprint.commons.response.SprintResponse;
import br.com.iesb.jira.application.incoming.sprint.commons.response.SprintTaskResponse;
import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.sprint.vo.SprintTaskVO;
import br.com.iesb.jira.domain.sprint.vo.SprintVO;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class SprintConverter {

    private SprintConverter() {
        throw new UnsupportedOperationException("can not be call constructor");
    }

    public static SprintVO of(final SprintRequest request) {
        return new SprintVO(request.sprintStartDate(), request.sprintEndDate(), request.projectId());
    }

    public static SprintVO of(final UUID sprintId, final SprintRequest request) {
        Set<SprintTaskVO> sprintTasksVO = request.sprintTasks().stream()
                .map(t -> new SprintTaskVO(t.taskId()))
                .collect(Collectors.toSet());

        return new SprintVO(sprintId, request.sprintStartDate(),
                request.sprintEndDate(), request.projectId(), sprintTasksVO);
    }

    public static SprintResponse toResponse(SprintVO sprintVO) {
        Set<SprintTaskResponse> sprintTasksResponse = sprintVO.sprintTasksVO().stream()
                .map(t -> new SprintTaskResponse(t.taskId(), t.taskCode(), t.taskTitle()))
                .collect(Collectors.toSet());
        
        return new SprintResponse(sprintVO.id(), sprintVO.sprintCode(),
                sprintVO.sprintStartDate(),sprintVO.sprintEndDate(), sprintVO.sprintProjectId(), sprintTasksResponse);
    }

    public static SprintResponse toResponse(Sprint sprint) {
        Set<SprintTaskResponse> sprintTasksResponse = sprint.getTasks().stream()
                .map(t -> new SprintTaskResponse(t.getId(), t.getTaskCode(), t.getTaskTitle()))
                .collect(Collectors.toSet());
        
        return new SprintResponse(sprint.getId(), sprint.getSprintCode(), sprint.getSprintStartDate(),
                sprint.getSprintEndDate(),sprint.getProject().getId(), sprintTasksResponse);
    }
}
