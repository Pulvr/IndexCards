package de.indexcards.indexcards.repository;

import de.indexcards.indexcards.classes.Deck;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface DeckRepository extends ListCrudRepository<Deck, Long> {

    @Query("""
           SELECT DECK.NAME from DECK
           JOIN USERS on DECK.user_id = USERS.ID
           WHERE lower(USERS.USERNAME) = lower(:name)
           """)
    List<Deck> findDecksByUserName(String name);

    //Find all Decks of a User by a single User ID
    @Query("""
           SELECT DECK.ID, DECK.NAME, DECK.USER_ID from DECK
           JOIN USERS on DECK.user_id = USERS.ID
           WHERE USERS.ID = :id
           """)
    List<Deck> findDecksByUserId(long id);

    @Query("SELECT USERS.CURRENTDECK FROM USERS WHERE USERS.ID = :id")
    int findCurrentDeckId(long id);
}
