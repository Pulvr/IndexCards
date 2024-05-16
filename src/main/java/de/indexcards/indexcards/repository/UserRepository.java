package de.indexcards.indexcards.repository;

import de.indexcards.indexcards.classes.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserRepository extends ListCrudRepository<User, Long> {

    @Query("SELECT * FROM Users WHERE USERNAME = :name")
    List<User> findByName(String name);

    @Modifying
    @Query("UPDATE USERS SET USERNAME = :name WHERE ID = :id")
    boolean updateName(long id, String name);
}
