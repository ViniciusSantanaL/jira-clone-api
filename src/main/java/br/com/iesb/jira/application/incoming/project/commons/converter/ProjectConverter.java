package br.com.iesb.jira.application.incoming.project.commons.converter;

import br.com.iesb.jira.application.incoming.project.commons.request.ProjectRequest;
import br.com.iesb.jira.application.incoming.project.commons.response.ProjectResponse;
import br.com.iesb.jira.application.incoming.project.commons.response.ProjectSprintResponse;
import br.com.iesb.jira.application.incoming.team.commons.response.TeamUserResponse;
import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.project.vo.ProjectSprintVO;
import br.com.iesb.jira.domain.project.vo.ProjectVO;
import br.com.iesb.jira.domain.user.vo.UserVO;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProjectConverter {

    private ProjectConverter() {
        throw new UnsupportedOperationException("can not be call constructor");
    }

    public static ProjectVO of(final ProjectRequest request) {
        Set<UserVO> usersVO = request.projectUsers().stream()
                .map(u -> new UserVO(u.userId()))
                .collect(Collectors.toSet());

        return new ProjectVO(request.projectName(), usersVO);
    }

    public static ProjectVO of(final UUID projectId, final ProjectRequest request) {
        Set<UserVO> usersVO = request.projectUsers().stream()
                .map(u -> new UserVO(u.userId()))
                .collect(Collectors.toSet());

        Set<ProjectSprintVO> sprintsVO = request.projectSprints().stream()
                .map(u -> new ProjectSprintVO(u.sprintId()))
                .collect(Collectors.toSet());

        return new ProjectVO(projectId,request.projectName(), null ,usersVO, sprintsVO);
    }

    public static ProjectResponse toResponse(ProjectVO projectVO) {
        Set<TeamUserResponse> projectUsers = projectVO.users().stream()
                .map(u -> new TeamUserResponse(u.userId(), u.username(), u.userEmail()))
                .collect(Collectors.toSet());

        Set<ProjectSprintResponse> projectSprints = projectVO.sprints().stream()
                .map(s -> new ProjectSprintResponse(s.sprintId(), s.projectCode()))
                .collect(Collectors.toSet());

        return new ProjectResponse(projectVO.id(), projectVO.projectName(),
                projectVO.projectCreateDate(), projectUsers, projectSprints);
    }

    public static ProjectResponse toResponse(Project project) {
        Set<TeamUserResponse> projectUsers = project.getUsers().stream()
                .map(u -> new TeamUserResponse(u.getId(), u.getUserUsername(), u.getUserEmail()))
                .collect(Collectors.toSet());

        Set<ProjectSprintResponse> projectSprints = project.getSprints().stream()
                .map(s -> new ProjectSprintResponse(s.getId(), s.getSprintCode()))
                .collect(Collectors.toSet());

        return new ProjectResponse(project.getId(), project.getProjectName(),
                project.getProjectCreateDate(), projectUsers, projectSprints);
    }
}
