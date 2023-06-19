package br.com.iesb.jira.domain.task.repository;

import br.com.iesb.jira.domain.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("select count(*) from task t inner join t.sprint where t.sprint.id = ?1")
    Integer fetchSizeTaskByProjectId(final UUID sprintId);
}
