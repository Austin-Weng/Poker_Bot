

import java.util.*;
public class Card {
    private String suit;

    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }


    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    public String getSuit() {
        return suit;
    }
    public String getRank() {
        return rank;
    }


    private int cardValue(Card card) {
        if(card.getRank() == "Jack") {
            return 11;
        }
        else if(card.getRank() == "Queen") {
            return 12;
        }
        else if(card.getRank() == "King") {
            return 13;
        }
        else if(card.getRank() == "Ace") {
            return 14;
        }
        return Integer.parseInt(card.getRank());
    }
}
