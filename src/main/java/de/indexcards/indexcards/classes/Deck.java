package de.indexcards.indexcards.classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("DECK")
public class Deck {


    @Id
    private long id;
    @Column("NAME")
    private String name;

    private long userId;

    public Deck(){}

    public Deck(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
    public long getUserId() {
        return userId;
    }

}
