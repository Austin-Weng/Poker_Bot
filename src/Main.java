import java.util.*;
public class Main {
    public static void main(String[] args) {
        AustinBot austinBot = new AustinBot(300);
        //PokerBot(class) = Harry bot
        LinkedList<Card> cardsInPlay = new LinkedList<Card>();
        LinkedList<Card> burnPile = new LinkedList<Card>();
        int pot = 0;
        Deck deck = new Deck();
        deck.initializeDeck();
        deck.shuffle();

        Player player = new Player(300);
        String[] orderOfPlay = {"austin", "harry", "human player"};

        //Deal cards
        austinBot.addCard(deck.deal());
        austinBot.addCard(deck.deal());
        harryBot.hand.add(deck.deal());
        harryBot.hand.add(deck.deal());
        player.addCard(deck.deal());
        player.addCard(deck.deal());
        System.out.println("Game initialized");
        System.out.println("Your Cards: " + );
    }
}