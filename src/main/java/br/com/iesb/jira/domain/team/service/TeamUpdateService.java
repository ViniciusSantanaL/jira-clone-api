package br.com.iesb.jira.domain.team.service;

import br.com.iesb.jira.domain.team.model.Team;
import br.com.iesb.jira.domain.team.repository.TeamRepository;
import br.com.iesb.jira.domain.team.vo.TeamVO;
import br.com.iesb.jira.domain.team.vo.builder.TeamBuilder;
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

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
public class TeamUpdateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamUpdateService.class);

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public TeamUpdateService(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public TeamVO updateTeamById(final TeamVO teamVO) {
        Team teamToSave = teamRepository.findById(teamVO.id())
                .orElseThrow(() -> new EntityNotFoundException("team", "team_id"));

        teamToSave.setTeamName(teamVO.teamName());
        List<UUID> usersVOId = teamVO.users().stream().map(UserVO::userId).toList();
        List<User> teamUsers = userRepository.findAllById(usersVOId);

        EntityValidation.validateIfEntityExist(usersVOId, teamUsers, "users");

        teamToSave.setUsers(new HashSet<>(teamUsers));
        Team teamSaved = saveTeam(teamToSave);

        return TeamBuilder.createTeamVO(teamSaved);
    }

    private Team saveTeam(Team team) {
        try {
            return teamRepository.save(team);
        } catch(DataIntegrityViolationException ex) {
            LOGGER.error("Error to updateTeam, {}",ex.getMessage());
            throw new EntityDuplicatedException("team", "team_name");
        }
    }
}
