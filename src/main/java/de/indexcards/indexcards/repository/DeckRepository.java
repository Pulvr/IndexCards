package de.indexcards.indexcards.repository;

import de.indexcards.indexcards.classes.Deck;
import de.indexcards.indexcards.classes.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface DeckRepository extends ListCrudRepository<Deck, Long> {

    @Query("""
           SELECT DECK.* from DECK
           JOIN USERS on DECK.user_id = USERS.ID
           WHERE lower(USERS.USERNAME) = lower(:name)
           """)
    List<User> findbyUserName(String name);
}
