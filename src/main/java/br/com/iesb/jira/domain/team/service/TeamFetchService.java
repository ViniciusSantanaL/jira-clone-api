package br.com.iesb.jira.domain.team.service;

import br.com.iesb.jira.domain.team.model.Team;
import br.com.iesb.jira.domain.team.repository.TeamRepository;
import br.com.iesb.jira.domain.team.vo.TeamVO;
import br.com.iesb.jira.domain.team.vo.builder.TeamBuilder;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TeamFetchService {

    private final TeamRepository teamRepository;

    public TeamFetchService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Page<Team> fetchAllTeam(final Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    public Team fetchTeamById(final UUID teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() ->  new EntityNotFoundException("team", "team_id"));
    }
}
