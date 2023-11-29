package org.example;
import java.util.*;


public class Deck {
    private final Stack<org.example.Card> cards;

    public Deck() {
        this.cards = new Stack<>();
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.push(new org.example.Card(suit, rank));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public org.example.Card deal() {
        return cards.pop();
    }

    public int getSize() {
        return cards.size();
    }
}


