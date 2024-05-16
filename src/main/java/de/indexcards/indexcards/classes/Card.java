package de.indexcards.indexcards.classes;

public class Card {
    private String cardFront;
    private String cardBack;

    Card(String cardFront, String cardBack) {
        this.cardFront = cardFront;
        this.cardBack = cardBack;
    }

    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }

    public String getCardBack() {
        return cardBack;
    }

    public void setCardBack(String cardBack) {
        this.cardBack = cardBack;
    }
}
