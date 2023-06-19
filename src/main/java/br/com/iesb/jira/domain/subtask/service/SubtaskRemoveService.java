package br.com.iesb.jira.domain.subtask.service;

import br.com.iesb.jira.domain.subtask.model.Subtask;
import br.com.iesb.jira.domain.subtask.repository.SubtaskRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubtaskRemoveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubtaskRemoveService.class);

    private final SubtaskRepository subtaskRepository;

    public SubtaskRemoveService(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }

    public void deleteSubtaskById(final UUID subtaskId) {
        LOGGER.info("deleteSubtaskById");
        Subtask subtask = subtaskRepository.findById(subtaskId).orElseThrow(() -> new EntityNotFoundException("subtask", "subtask_id"));
        subtaskRepository.deleteById(subtask.getId());
        LOGGER.info("subtask delete by id: {}",subtaskId);
    }

}
