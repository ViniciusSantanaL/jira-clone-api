package br.com.iesb.jira.domain.task.model;

import br.com.iesb.jira.domain.comment.model.Comment;
import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.subtask.model.Subtask;
import br.com.iesb.jira.domain.user.model.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity(name = "task")
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "task_code", nullable = false)
    private Integer taskCode;

    @Column(name = "task_title", nullable = false)
    private String taskTitle;

    @Column(name = "task_description", nullable = false)
    private String taskDescription;

    @Column(name = "task_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "task_start_date")
    private LocalDate taskStartDate;

    @Column(name = "task_end_date")
    private LocalDate taskEndDate;

    @ManyToOne
    @JoinColumn(name = "id_task_owner", referencedColumnName = "id", foreignKey = @ForeignKey(name = "task_user_fk"))
    private User taskOwner;

    @ManyToOne
    @JoinColumn(name ="id_sprint", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "task_sprint_fk"), nullable = false)
    private Sprint sprint;

    @OneToMany(mappedBy = "task",cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE },
            fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "task", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE },
            fetch = FetchType.LAZY)
    private Set<Subtask> subtasks;

    @LastModifiedDate
    @Column(name = "last_modification_date", nullable = false)
    private LocalDateTime lastModificationDate;

    @Version
    private Long version;

    public Task() {}

    public UUID getId() {
        return id;
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public Long getVersion() {
        return version;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Integer getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(Integer taskCode) {
        this.taskCode = taskCode;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDate getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(LocalDate taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public LocalDate getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(LocalDate taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public User getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(User taskOwner) {
        this.taskOwner = taskOwner;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(Set<Subtask> subtasks) {
        this.subtasks = subtasks;
    }
}