package br.com.iesb.jira.domain.comment.model;

import br.com.iesb.jira.domain.task.model.Task;
import br.com.iesb.jira.domain.user.model.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "comment_description", nullable = false)
    private String commentDescription;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "comment_user_fk"), nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "comment_task_fk"), nullable = false)
    private Task task;

    @LastModifiedDate
    @Column(name = "last_modification_date", nullable = false)
    private LocalDateTime lastModificationDate;

    @Version
    private Long version;

    public Comment() {}

    public UUID getId() {
        return id;
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public Long getVersion() {
        return version;
    }

    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}