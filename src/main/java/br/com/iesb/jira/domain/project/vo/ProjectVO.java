package br.com.iesb.jira.domain.project.vo;

import br.com.iesb.jira.domain.user.vo.UserVO;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record ProjectVO(UUID id,
                        String projectName,
                        LocalDate projectCreateDate,
                        Set<UserVO> users,
                        Set<ProjectSprintVO> sprints) {
    public ProjectVO(String projectName, Set<UserVO> users) {
        this(null, projectName, null, users, null);
    }

    public ProjectVO(String projectName, Set<UserVO> users, Set<ProjectSprintVO> sprintIds) {
        this(null, projectName, null, users, sprintIds);
    }
}
