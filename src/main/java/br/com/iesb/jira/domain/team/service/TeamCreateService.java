package br.com.iesb.jira.domain.team.service;

import br.com.iesb.jira.domain.team.model.Team;
import br.com.iesb.jira.domain.team.repository.TeamRepository;
import br.com.iesb.jira.domain.team.vo.TeamVO;
import br.com.iesb.jira.domain.team.vo.builder.TeamBuilder;
import br.com.iesb.jira.domain.user.model.User;
import br.com.iesb.jira.domain.user.repository.UserRepository;
import br.com.iesb.jira.domain.user.vo.UserVO;
import br.com.iesb.jira.infrastructure.exception.EntityDuplicatedException;
import br.com.iesb.jira.infrastructure.utils.validation.EntityValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamCreateService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamCreateService.class);
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public TeamCreateService(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public TeamVO createTeam(TeamVO teamVO) {
        List<UUID> usersVOId = teamVO.users().stream().map(UserVO::userId).toList();
        List<User> users = userRepository.findAllById(usersVOId);

        EntityValidation.validateIfEntityExist(usersVOId, users, "team");

        Team team = saveTeam(TeamBuilder.create(teamVO, users));
        LOGGER.info("createTeam, Team: {}", team);

        return TeamBuilder.createTeamVO(team);
    }

    private Team saveTeam(final Team team) {
        try {
            return teamRepository.save(team);
        } catch (DataIntegrityViolationException ex) {
            LOGGER.error("Error to saveTeam: {}", ex.getMessage());
            throw new EntityDuplicatedException("team","teamName");
        }
    }
}
