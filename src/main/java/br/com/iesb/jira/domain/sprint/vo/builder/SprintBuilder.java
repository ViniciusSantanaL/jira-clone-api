package br.com.iesb.jira.domain.sprint.vo.builder;

import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.sprint.vo.SprintVO;
import br.com.iesb.jira.domain.sprint.vo.SprintTaskVO;
import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.domain.user.vo.UserVO;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SprintBuilder {
    private SprintBuilder() {
        throw new UnsupportedOperationException("cannot be call constructor");
    }

    public static Sprint create(SprintVO sprintVO, Integer sprintCode,Project project) {
        Sprint sprint = new Sprint();
        sprint.setProject(project);
        sprint.setSprintStartDate(sprintVO.sprintStartDate());
        sprint.setSprintEndDate(sprintVO.sprintEndDate());
        sprint.setTasks(Collections.emptySet());
        sprint.setSprintCode(sprintCode);
        return sprint;
    }

    public static SprintVO createSprintVO(Sprint sprint) {
        Set<SprintTaskVO> sprintTasksVO = sprint.getTasks().stream()
                .map(t -> new SprintTaskVO(t.getId(), t.getTaskCode(), t.getTaskTitle()))
                .collect(Collectors.toSet());

        return new SprintVO(sprint.getId(),
                sprint.getSprintCode(),
                sprint.getSprintStartDate(),
                sprint.getSprintEndDate(),
                sprint.getProject().getId(),
                sprintTasksVO);
    }
}
