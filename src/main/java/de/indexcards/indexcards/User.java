package de.indexcards.indexcards;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User {
    private String name;
    private String username;
    private String password;
    private List<Card> userCards = new LinkedList<Card>();


    public static void main(String[] args) {
        Card card1 = new Card("Hello", "Hallo");
        User user1 = new User();

        user1.getUserCards().add(card1);

        System.out.println(user1.getUserCards().get(0).getCardFront());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Card> getUserCards() {
        return userCards;
    }
}
