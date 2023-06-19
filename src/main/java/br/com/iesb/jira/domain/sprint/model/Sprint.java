package br.com.iesb.jira.domain.sprint.model;

import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.task.model.Task;
import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity(name = "sprint")
@EntityListeners(AuditingEntityListener.class)
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "spring_code", nullable = false)
    private Integer sprintCode;

    @Column(name = "sprint_start_date", nullable = false)
    private LocalDate sprintStartDate;

    @Column(name = "sprint_end_date", nullable = false)
    private LocalDate sprintEndDate;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "sprint_project_fk"), nullable = false)
    private Project project;

    @OneToMany(mappedBy = "sprint", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE },
            fetch = FetchType.LAZY)
    private Set<Task> tasks;

    @LastModifiedDate
    @Column(name = "last_modification_date", nullable = false)
    private LocalDateTime lastModificationDate;

    @Version
    private Long version;

    public Sprint() {}

    public UUID getId() {
        return id;
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public Long getVersion() {
        return version;
    }

    public Integer getSprintCode() {
        return sprintCode;
    }

    public void setSprintCode(Integer sprintCode) {
        this.sprintCode = sprintCode;
    }

    public LocalDate getSprintStartDate() {
        return sprintStartDate;
    }

    public void setSprintStartDate(LocalDate sprintStartDate) {
        this.sprintStartDate = sprintStartDate;
    }

    public LocalDate getSprintEndDate() {
        return sprintEndDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setSprintEndDate(LocalDate sprintEndDate) {
        this.sprintEndDate = sprintEndDate;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}