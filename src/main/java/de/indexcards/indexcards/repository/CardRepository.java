package de.indexcards.indexcards.repository;

import de.indexcards.indexcards.classes.Card;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CardRepository extends ListCrudRepository<Card,Long> {
    @Query("""
            Select CARDS.DECK_ID, CARDS.ID, CARDS.Front, CARDS.BACK FROM 
            USERS join DECK on DECK.USER_ID = USERS.ID JOIN CARDS 
            on CARDS.DECK_ID = DECK.ID WHERE USERS.ID = :id
            """)
    List<Card> findAllCardsByUserId(@Param("id") Long id);

    @Query("""
            Select CARDS.DECK_ID, CARDS.ID, CARDS.Front, CARDS.BACK FROM 
            USERS join DECK on DECK.USER_ID = USERS.ID JOIN CARDS on CARDS.DECK_ID = DECK.ID 
            WHERE USERS.ID = :id AND CARDS.DECK_ID = :deck_Id
            """)
    List<Card> findAllCardsByUserAndDeckId(@Param("id") Long id, @Param("deck_Id") Long deck_Id);

    @Transactional
    @Modifying
    @Query("INSERT INTO CARDS( DECK_ID, ID, Front, BACK ) VALUES ( :deck_id,:id, :front, :back )")
    void addCard(long deck_id, long id, String front, String back);
}
