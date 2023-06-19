package br.com.iesb.jira.domain.sprint.repository;

import br.com.iesb.jira.domain.sprint.model.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, UUID> {

    @Query("select count(*) from sprint s inner join s.project where s.project.id = ?1")
    Integer fetchSizeSprintByProjectId(final UUID projectId);
}
