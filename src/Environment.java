import java.util.Stack;
import java.util.*;

public class Environment {
    public void dealCards(Stack deck, HashSet<Card> bot1Hand, HashSet<Card> bot2Hand, HashSet<Card> playerHand){
        bot1Hand.add(deck.deal);
        bot1Hand.add(deck.deal);
        bot2Hand.add(deck.deal);
        bot2Hand.add(deck.deal);
        playerHand.add(deck.deal);
        playerHand.add(deck.deal);
    }

    public void round1(LinkedList<Card> cardsInPlay, int pot, LinkedList<Card> burnpile, Stack deck, String[] orderOfPlay,
                       HashSet<Card> bot1Hand, HashSet<Card> bot2Hand, HashSet<Card> playerHand){

        //deal cards to players

        //take decisions
        for (String player : orderOfPlay){
            player.desicion
        }

    }


}
