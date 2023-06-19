package br.com.iesb.jira.domain.sprint.service;

import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.sprint.repository.SprintRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SprintRemoveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SprintRemoveService.class);

    private final SprintRepository sprintRepository;

    public SprintRemoveService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public void deleteSprintById(final UUID sprintId) {
        LOGGER.info("deleteSprintById");
        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(() -> new EntityNotFoundException("sprint", "sprint_id"));
        sprintRepository.deleteById(sprint.getId());
        LOGGER.info("sprint delete by id: {}",sprintId);
    }

}
