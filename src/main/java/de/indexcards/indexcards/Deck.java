package de.indexcards.indexcards;

import java.util.LinkedList;
import java.util.List;

public class Deck {
    private String name;
    private List<Card> cardsInDeck = new LinkedList<Card>();

    public Deck(String name) {}

    public void addCard(Card card) {
        cardsInDeck.add(card);
    }

    public Card getCard(int index) {
        return cardsInDeck.get(index);
    }
}
