package br.com.iesb.jira.domain.project.service;

import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.project.repository.ProjectRepository;
import br.com.iesb.jira.domain.project.vo.ProjectSprintVO;
import br.com.iesb.jira.domain.project.vo.ProjectVO;
import br.com.iesb.jira.domain.project.vo.builder.ProjectBuilder;
import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.sprint.repository.SprintRepository;
import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.domain.user.repository.UserRepository;
import br.com.iesb.jira.domain.user.vo.UserVO;
import br.com.iesb.jira.infrastructure.exception.EntityDuplicatedException;
import br.com.iesb.jira.infrastructure.utils.validation.EntityValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectCreateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectCreateService.class);
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    private final SprintRepository sprintRepository;

    public ProjectCreateService(ProjectRepository projectRepository, UserRepository userRepository,
                                SprintRepository sprintRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.sprintRepository = sprintRepository;
    }

    public ProjectVO createProject(ProjectVO projectVO) {
        Set<User> projectUsersToSave = Collections.emptySet();

        projectUsersToSave = createProjectUsers(projectVO, projectUsersToSave, userRepository);

        Project project = saveProject(ProjectBuilder.create(projectVO, projectUsersToSave));

        LOGGER.info("createProject, Project: {}", project);

        return ProjectBuilder.createProjectVO(project);
    }

    private Set<User> createProjectUsers(ProjectVO projectVO, Set<User> projectUsersToSave, UserRepository userRepository) {
        if(!projectVO.users().isEmpty()) {
            List<UUID> usersVOId = projectVO.users().stream().map(UserVO::userId).toList();
            List<User> users = userRepository.findAllById(usersVOId);

            EntityValidation.validateIfEntityExist(usersVOId, users, "users");

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
