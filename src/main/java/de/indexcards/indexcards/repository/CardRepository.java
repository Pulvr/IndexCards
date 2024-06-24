package de.indexcards.indexcards.repository;

import de.indexcards.indexcards.classes.Card;
import org.springframework.context.event.EventListener;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

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
    @Query("INSERT INTO CARDS( DECK_ID, Front, BACK ) VALUES ( :deck_id, :front, :back )")
    void addCard(long deck_id, String front, String back);

    @Transactional
    @Modifying
    @Query("DELETE FROM CARDS WHERE CARDS.ID = :id ")
    void deleteCard(long id);
}
