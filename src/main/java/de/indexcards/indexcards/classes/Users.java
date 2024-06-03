package de.indexcards.indexcards.classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERS")
public class Users {

    @Id
    private long id;

    @Column("USERNAME")
    private String userName;

    @Column("FIRSTNAME")
    private String firstName;

    @Column("LASTNAME")
    private String lastName;

    private String email;

    private String password;

    //Soll das aktuell gewählte Deck liefern
    private int currentDeck;

    public Users(){

    }

    public Users(String firstName, String lastName, String userName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public long getId() {
        return id;
    }
    public int getCurrentDeck() {
        return currentDeck;
    }

}
