package br.com.iesb.jira.domain.subtask.model;

import br.com.iesb.jira.domain.task.model.Task;
import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "subtask")
@EntityListeners(AuditingEntityListener.class)
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "substask_title", nullable = false)
    private String subTasktitle;
    @Column(name = "subtask_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SubtaskStatus subtaskStatus;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "subtask_task_fk"), nullable = false)
    private Task task;

    @LastModifiedDate
    @Column(name = "last_modification_date", nullable = false)
    private LocalDateTime lastModificationDate;

    @Version
    private Long version;

    public Subtask() {}

    public UUID getId() {
        return id;
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public Long getVersion() {
        return version;
    }

    public String getSubTasktitle() {
        return subTasktitle;
    }

    public void setSubTasktitle(String subTasktitle) {
        this.subTasktitle = subTasktitle;
    }

    public SubtaskStatus getSubtaskStatus() {
        return subtaskStatus;
    }

    public void setSubtaskStatus(SubtaskStatus subtaskStatus) {
        this.subtaskStatus = subtaskStatus;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}