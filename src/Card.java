package org.example;

import java.util.*;
public class Card {
    private final String suit;
    private final String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }


    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
