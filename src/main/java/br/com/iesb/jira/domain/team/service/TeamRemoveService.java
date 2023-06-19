package br.com.iesb.jira.domain.team.service;

import br.com.iesb.jira.domain.team.model.Team;
import br.com.iesb.jira.domain.team.repository.TeamRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeamRemoveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamRemoveService.class);

    private final TeamRepository teamRepository;

    public TeamRemoveService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void deleteTeamById(final UUID teamId) {
        LOGGER.info("deleteTeamById");
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("team", "team_id"));
        teamRepository.deleteById(team.getId());
        LOGGER.info("team delete by id: {}",teamId);
    }

}
