package br.com.iesb.jira.domain.project.vo;

import br.com.iesb.jira.domain.user.vo.UserVO;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record ProjectVO(UUID id,
                        String projectName,

                        UUID teamId,
                        LocalDate projectCreateDate,
                        Set<UserVO> users,
                        Set<ProjectSprintVO> sprints) {

    public ProjectVO(UUID id) {
        this(id, null,null, null, null, null);
    }

    public ProjectVO(UUID id, String projectName) {
        this(id, projectName, null,null, null, null);
    }

    public ProjectVO(String projectName, Set<UserVO> users, UUID teamId) {
        this(null, projectName, teamId,null, users, null);
    }

    public ProjectVO(String projectName, Set<UserVO> users, Set<ProjectSprintVO> sprintIds) {
        this(null, projectName, null, null, users, sprintIds);
    }
}
