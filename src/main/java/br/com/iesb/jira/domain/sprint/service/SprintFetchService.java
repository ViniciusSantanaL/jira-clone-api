package br.com.iesb.jira.domain.sprint.service;

import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.sprint.repository.SprintRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SprintFetchService {

    private final SprintRepository sprintRepository;

    public SprintFetchService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public Page<Sprint> fetchAllSprint(final Pageable pageable) {
        return sprintRepository.findAll(pageable);
    }

    public Sprint fetchSprintById(final UUID sprintId) {
        return sprintRepository.findById(sprintId)
                .orElseThrow(() ->  new EntityNotFoundException("sprint", "sprint_id"));
    }
}
