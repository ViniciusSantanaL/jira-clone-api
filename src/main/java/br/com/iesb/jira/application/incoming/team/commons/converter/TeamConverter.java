package br.com.iesb.jira.application.incoming.team.commons.converter;

import br.com.iesb.jira.application.incoming.team.commons.request.TeamRequest;
import br.com.iesb.jira.application.incoming.team.commons.response.TeamProjectResponse;
import br.com.iesb.jira.application.incoming.team.commons.response.TeamResponse;
import br.com.iesb.jira.application.incoming.team.commons.response.TeamUserResponse;
import br.com.iesb.jira.domain.project.vo.ProjectVO;
import br.com.iesb.jira.domain.team.model.Team;
import br.com.iesb.jira.domain.team.vo.TeamVO;
import br.com.iesb.jira.domain.user.vo.UserVO;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class TeamConverter {

    private TeamConverter() {
        throw new UnsupportedOperationException("can not be call constructor");
    }

    public static TeamVO of(final TeamRequest request) {
        Set<UserVO> usersVO = request.teamUsers().stream()
                .map(u -> new UserVO(u.userId()))
                .collect(Collectors.toSet());
        return new TeamVO(request.teamName(), usersVO);
    }

    public static TeamVO of(final UUID teamId, final TeamRequest request) {
        Set<UserVO> usersVO = request.teamUsers().stream()
                .map(u -> new UserVO(u.userId()))
                .collect(Collectors.toSet());
        Set<ProjectVO> projectsVO = request.teamProjects().stream()
                .map(p -> new ProjectVO(p.projectId()))
                .collect(Collectors.toSet());
        return new TeamVO(teamId,request.teamName(), usersVO, projectsVO);
    }

    public static TeamResponse toResponse(final TeamVO teamVO) {
        Set<TeamUserResponse> teamUsers = teamVO.users().stream()
                .map(u -> new TeamUserResponse(u.userId(), u.username(), u.userEmail()))
                .collect(Collectors.toSet());
        Set<TeamProjectResponse> projectResponse = teamVO.projects().stream()
                .map(p -> new TeamProjectResponse(p.id(), p.projectName()))
                .collect(Collectors.toSet());
        return new TeamResponse(teamVO.id(), teamVO.teamName(), teamUsers, projectResponse);
    }

    public static TeamResponse toResponse(final Team team) {
        Set<TeamUserResponse> teamUsers = team.getUsers().stream()
                .map(u -> new TeamUserResponse(u.getId(), u.getUserUsername(), u.getUserEmail()))
                .collect(Collectors.toSet());
        Set<TeamProjectResponse> projectResponse = team.getProjects().stream()
                .map(p -> new TeamProjectResponse(p.getId(), p.getProjectName()))
                .collect(Collectors.toSet());
        return new TeamResponse(team.getId(), team.getTeamName(), teamUsers, projectResponse);
    }
}
