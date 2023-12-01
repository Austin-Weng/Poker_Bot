import java.util.*;
public class Main {
    public static void main(String[] args) {
        AustinBot austinBot = new AustinBot(300);
        HarryBot harryBot = new HarryBot(300);
        HashSet<Card> cardsInPlay = new HashSet<Card>();
        HashSet<Card> burnPile = new HashSet<Card>();
        int pot = 0;
        Deck deck = new Deck();
        deck.initializeDeck();
        deck.shuffle();

        Player player = new Player(300);
        String[] orderOfPlay = {"austin", "harry", "human player"};
        String[] decisions = new String[3];
        while (!decisions[0].equals("raise") && )

        //Deal cards to all players
        austinBot.addCard(deck.deal());
        austinBot.addCard(deck.deal());
        harryBot.addCard(deck.deal());
        harryBot.addCard(deck.deal());
        player.addCard(deck.deal());
        player.addCard(deck.deal());

        //Round zero


        //Reveal the flop
        burnPile.add(deck.deal());
        cardsInPlay.add(deck.deal());
        cardsInPlay.add(deck.deal());
        cardsInPlay.add(deck.deal());
        System.out.println("Game initialized");
        System.out.println("Your Cards: " + player.getHand());
        System.out.println("Cards in play: " + cardsInPlay);

        //while none of the decisions are raise
        System.out.println("Austin bot decision: " + austinBot.decision(cardsInPlay, pot));
        System.out.println("Harry bot decision: " + harryBot.decision(cardsInPlay, pot));
        System.out.println("What would you like to do? (fold/check/call/raise)");
        Scanner scan = new Scanner(System.in);
        String playerDecision = scan.next();

        //ROUND 1
        burnPile.add(deck.deal());
        cardsInPlay.add(deck.deal());
        while ()
        for (String person : orderOfPlay){

        }
    }
}