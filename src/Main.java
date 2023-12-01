import java.util.*;
public class Main {
    public static void main(String[] args) {
        //PokerBot(class) = Austin bot
        //PokerBot(class) = Harry bot
        LinkedList<Card> cardsInPlay = new LinkedList<Card>();
        LinkedList<Card> burnPile = new LinkedList<Card>();
        int pot = 0;
        Deck deck = new Deck();
        deck.initializeDeck();
        deck.shuffle();
        String[] orderOfPlay = {"austin", "harry", "human player"};

        //Deal cards
        austinBot.hand.add(deck.deal);
        austinBot.hand.add(deck.deal);
        harryBot.hand.add(deck.deal);
        harryBot.hand.add(deck.deal);
        player.addCard(deck.deal);
        player.addCard(deck.deal);
    }
}