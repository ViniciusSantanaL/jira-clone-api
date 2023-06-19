package br.com.iesb.jira.domain.team.model;

import br.com.iesb.jira.domain.project.model.Project;
import br.com.iesb.jira.domain.user.model.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity(name = "team")
@EntityListeners(AuditingEntityListener.class)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "team_name", nullable = false, unique = true)
    private String teamName;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            fetch = FetchType.LAZY)
    private Set<User> users;

    @OneToMany(mappedBy = "team",cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE },
            fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Project> projects;

    @LastModifiedDate
    @Column(name = "last_modification_date", nullable = false)
    private LocalDateTime lastModificationDate;

    @Version
    private Long version;

    public Team() {}

    public UUID getId() {
        return id;
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Long getVersion() {
        return version;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", users=" + users +
                '}';
    }
}
