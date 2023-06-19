package br.com.iesb.jira.domain.project.service;

import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.project.repository.ProjectRepository;
import br.com.iesb.jira.domain.project.vo.ProjectSprintVO;
import br.com.iesb.jira.domain.project.vo.ProjectVO;
import br.com.iesb.jira.domain.project.vo.builder.ProjectBuilder;
import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.sprint.repository.SprintRepository;
import br.com.iesb.jira.domain.team.model.Team;
import br.com.iesb.jira.domain.team.repository.TeamRepository;
import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.domain.user.repository.UserRepository;
import br.com.iesb.jira.domain.user.vo.UserVO;
import br.com.iesb.jira.infrastructure.exception.EntityDuplicatedException;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import br.com.iesb.jira.infrastructure.utils.validation.EntityValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectUpdateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectUpdateService.class);

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    private final TeamRepository teamRepository;

    private final SprintRepository sprintRepository;

    public ProjectUpdateService(ProjectRepository projectRepository, UserRepository userRepository, TeamRepository teamRepository, SprintRepository sprintRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.sprintRepository = sprintRepository;
    }

    public ProjectVO updateProjectById(final ProjectVO projectVO) {
        Project projectToSave = projectRepository.findById(projectVO.id())
                .orElseThrow(() -> new EntityNotFoundException("project", "project_id"));

        Team teamToSave = teamRepository.findById(projectVO.teamId())
                .orElseThrow(() -> new EntityNotFoundException("team", "team_id"));

        Set<User> projectUsersToSave = Collections.emptySet();
        Set<Sprint> projectSprintsToSave = Collections.emptySet();

        projectUsersToSave = updateProjectUsers(projectVO, projectUsersToSave, userRepository);

        projectSprintsToSave = updateSprint(projectVO, projectSprintsToSave);

        projectToSave.setProjectName(projectVO.projectName());
        projectToSave.setProjectCreateDate(projectVO.projectCreateDate());
        projectToSave.setUsers(projectUsersToSave);
        projectToSave.setSprints(projectSprintsToSave);
        projectToSave.setTeam(teamToSave);
        Project projectSaved = saveProject(projectToSave);

        return ProjectBuilder.createProjectVO(projectSaved);
    }

    private Set<Sprint> updateSprint(ProjectVO projectVO, Set<Sprint> projectSprintsToSave) {
        if(!projectVO.sprints().isEmpty()) {
            List<UUID> sprintsVOId = projectVO.sprints().stream().map(ProjectSprintVO::sprintId).toList();
            List<Sprint> sprints = sprintRepository.findAllById(sprintsVOId);

            EntityValidation.validateIfEntityExist(sprintsVOId,
                    sprints.stream().map(Sprint::getId).collect(Collectors.toSet()),
                    "sprint");

            projectSprintsToSave = new HashSet<>(sprints);

        }
        return projectSprintsToSave;
    }

    private Set<User> updateProjectUsers(ProjectVO projectVO, Set<User> projectUsersToSave, UserRepository userRepository) {
        if(!projectVO.users().isEmpty()) {
            List<UUID> usersVOId = projectVO.users().stream().map(UserVO::userId).toList();
            List<User> users = userRepository.findAllById(usersVOId);

            EntityValidation.validateIfEntityExist(usersVOId,
                    users.stream().map(User::getId).collect(Collectors.toSet()),
                    "users");

            projectUsersToSave = new HashSet<>(users);
        }
        return projectUsersToSave;
    }

    private Project saveProject(Project project) {
        try {
            return projectRepository.save(project);
        } catch(DataIntegrityViolationException ex) {
            LOGGER.error("Error to updateProject, {}",ex.getMessage());
            throw new EntityDuplicatedException("project", "project_name");
        }
    }
}
