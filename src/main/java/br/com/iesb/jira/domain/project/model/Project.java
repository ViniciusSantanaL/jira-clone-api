package br.com.iesb.jira.domain.project.model;

import br.com.iesb.jira.domain.sprint.model.Sprint;
import br.com.iesb.jira.domain.team.model.Team;
import br.com.iesb.jira.domain.user.model.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity(name = "project")
@EntityListeners(AuditingEntityListener.class)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "project_name", nullable = false, unique = true)
    private String projectName;

    @Column(name = "project_create_date", nullable = false)
    private LocalDate projectCreateDate;

    @OneToMany(mappedBy = "project", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE },
            fetch = FetchType.LAZY)
    private Set<Sprint> sprints;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY)
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "project_team_fk"), nullable = false)
    private Team team;

    @LastModifiedDate
    @Column(name = "last_modification_date", nullable = false)
    private LocalDateTime lastModificationDate;

    @Version
    private Long version;

    public Project() {}

    public UUID getId() {
        return id;
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getVersion() {
        return version;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getProjectCreateDate() {
        return projectCreateDate;
    }

    public void setProjectCreateDate(LocalDate projectCreateDate) {
        this.projectCreateDate = projectCreateDate;
    }

    public Set<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}