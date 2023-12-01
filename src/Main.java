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
        String[] orderOfPlay = {"bot 1", "bot 2", "human player"};
        dealCards(deck, austinBot.hand, harryBot.hand, playerHand);
    }

    public void dealCards(Stack deck, HashSet<Card> bot1Hand, HashSet<Card> bot2Hand, HashSet<Card> playerHand){
        bot1Hand.add(deck.deal);
        bot1Hand.add(deck.deal);
        bot2Hand.add(deck.deal);
        bot2Hand.add(deck.deal);
        playerHand.add(deck.deal);
        playerHand.add(deck.deal);
    }
}