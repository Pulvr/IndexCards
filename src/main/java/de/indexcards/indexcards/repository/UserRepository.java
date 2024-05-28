package de.indexcards.indexcards.repository;

import de.indexcards.indexcards.classes.Users;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserRepository extends ListCrudRepository<Users, Long> {

    @Query("SELECT * FROM Users WHERE USERNAME = :name")
    List<Users> findByName(String name);

    @Query("SELECT * FROM USERS WHERE ID = :id")
    Users findByUserId(long id);

    @Modifying
    @Query("UPDATE USERS SET USERNAME = :name WHERE ID = :id")
    boolean updateName(long id, String name);
}
