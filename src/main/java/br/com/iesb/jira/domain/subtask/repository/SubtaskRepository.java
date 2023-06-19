package br.com.iesb.jira.domain.subtask.repository;

import br.com.iesb.jira.domain.subtask.model.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubtaskRepository extends JpaRepository<Subtask, UUID> {
}
