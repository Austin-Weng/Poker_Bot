

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

    public String getSuit() {
        return suit;
    }
    public String getRank() {
        return rank;
    }


    public int cardValue(Card card) {
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
