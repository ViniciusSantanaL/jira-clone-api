package br.com.iesb.nicetask.domain.user.repository;

import br.com.iesb.nicetask.domain.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'name': ?0}")
    User findByUsername(String username);

}
