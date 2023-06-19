package br.com.iesb.jira.domain.project.vo.builder;

import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.project.vo.ProjectSprintVO;
import br.com.iesb.jira.domain.project.vo.ProjectVO;
import br.com.iesb.jira.domain.team.model.Team;
import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.domain.user.vo.UserVO;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProjectBuilder {
    private ProjectBuilder() {
        throw new UnsupportedOperationException("cannot be call constructor");
    }

    public static Project create(ProjectVO projectVO, Team team, Set<User> users) {
        Project project = new Project();
        project.setProjectName(projectVO.projectName());
        project.setTeam(team);
        project.setUsers(new HashSet<>(users));
        project.setProjectCreateDate(LocalDate.now());
        project.setSprints(Collections.emptySet());
        return project;
    }

    public static ProjectVO createProjectVO(Project project) {
        Set<UserVO> usersVO = project.getUsers().stream()
                .map(u -> new UserVO(u.getId(), u.getUsername(), u.getUserEmail()))
                .collect(Collectors.toSet());


        Set<ProjectSprintVO> sprintVOs = project.getSprints().stream()
                .map(s -> new ProjectSprintVO(s.getId(), s.getSprintCode()))
                .collect(Collectors.toSet());

        return new ProjectVO(project.getId(),
                project.getProjectName(),
                project.getTeam().getId(),
                project.getProjectCreateDate(),
                usersVO,
                sprintVOs);
    }
}
