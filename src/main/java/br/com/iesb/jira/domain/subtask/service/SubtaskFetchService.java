package br.com.iesb.jira.domain.subtask.service;

import br.com.iesb.jira.domain.subtask.model.Subtask;
import br.com.iesb.jira.domain.subtask.repository.SubtaskRepository;
import br.com.iesb.jira.infrastructure.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SubtaskFetchService {

    private final SubtaskRepository subtaskRepository;

    public SubtaskFetchService(SubtaskRepository subtaskRepository) {
        this.subtaskRepository = subtaskRepository;
    }

    public Page<Subtask> fetchAllSubtask(final Pageable pageable) {
        return subtaskRepository.findAll(pageable);
    }

    public Subtask fetchSubtaskById(final UUID subtaskId) {
        return subtaskRepository.findById(subtaskId)
                .orElseThrow(() ->  new EntityNotFoundException("subtask", "subtask_id"));
    }
}
