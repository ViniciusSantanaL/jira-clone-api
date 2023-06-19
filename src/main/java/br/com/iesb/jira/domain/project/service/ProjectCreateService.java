package br.com.iesb.jira.domain.project.service;

import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.project.repository.ProjectRepository;
import br.com.iesb.jira.domain.project.vo.ProjectVO;
import br.com.iesb.jira.domain.project.vo.builder.ProjectBuilder;
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
public class ProjectCreateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCreateService.class);
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    private final TeamRepository teamRepository;

    public ProjectCreateService(ProjectRepository projectRepository, UserRepository userRepository, TeamRepository teamRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
    }


    public ProjectVO createProject(ProjectVO projectVO) {
        Team teamToSave = teamRepository.findById(projectVO.teamId())
                .orElseThrow(() -> new EntityNotFoundException("team", "team_id"));
        Set<User> projectUsersToSave = Collections.emptySet();

        projectUsersToSave = createProjectUsers(projectVO, projectUsersToSave, userRepository);

        Project project = saveProject(ProjectBuilder.create(projectVO, teamToSave,projectUsersToSave));

        LOGGER.info("createProject, Project: {}", project);

        return ProjectBuilder.createProjectVO(project);
    }

    private Set<User> createProjectUsers(ProjectVO projectVO, Set<User> projectUsersToSave, UserRepository userRepository) {
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

    private Project saveProject(final Project project) {
        try {
            return projectRepository.save(project);
        } catch (DataIntegrityViolationException ex) {
            LOGGER.error("Error to saveProject: {}", ex.getMessage());
            throw new EntityDuplicatedException("project","projectName");
        }
    }
}
