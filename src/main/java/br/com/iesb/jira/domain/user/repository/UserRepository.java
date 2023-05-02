package br.com.iesb.jira.domain.user.repository;

import br.com.iesb.jira.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("select user_jira from user_jira as user_jira where user_jira.userUsername = :username")
    User findByUsername(@Param("username") String username);

}
