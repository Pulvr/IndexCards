package de.indexcards.indexcards.classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CARDS")
public class Card {
    @Id
    private long id;
    @Column("FRONT")
    private String front;
    @Column("BACK")
    private String back;


    @Column("DECK_ID")
    private String deck_id;

    public Card() {}

    Card(String front, String back, String deck_id) {
        this.front = front;
        this.back = back;
        this.deck_id = deck_id;
    }

    public long getId() {
        return id;
    }

    public String getDeck_id() {
        return deck_id;
    }

}
