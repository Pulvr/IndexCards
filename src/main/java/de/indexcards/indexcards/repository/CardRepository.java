package de.indexcards.indexcards.repository;

import de.indexcards.indexcards.classes.Card;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends ListCrudRepository<Card,Long> {
    @Query("""
            Select CARDS.DECK_ID, CARDS.ID, CARDS.Front, CARDS.BACK FROM 
            USERS join DECK on DECK.USER_ID = USERS.ID JOIN CARDS 
            on CARDS.DECK_ID = DECK.ID WHERE USERS.ID = :id
            """)
    List<Card> findAllCardsByUserId(@Param("id") Long id);
}
