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


        //while none of the decisions are raise
        System.out.println("Austin bot decision: " + austinBot.decision(cardsInPlay, pot));
        System.out.println("Harry bot decision: " + harryBot.decision(cardsInPlay, pot));
        System.out.println("What would you like to do? (fold/check/call/raise)");
        Scanner scan = new Scanner(System.in);
        String playerDecision = scan.next();


        for (String person : orderOfPlay) {
            if (person.equals("austin")) {
                decisions[0] = austinBot.decision(cardsInPlay, pot);
                System.out.println("Austin bot decision: " + decisions[0]);
            } else if (person.equals("harry")) {
                decisions[1] = harryBot.decision(cardsInPlay, pot);
                System.out.println("Harry bot decision: " + decisions[1]);
            } else {
                decisions[2] = playerDecision
                System.out.println("Your decision: " + decisions[2]);
            }
        }
        for (String decision : decisions) {
            if ("check".equals(decision)) {
            }
            else if ("raise".equals(decision)) {
                int raiseAmount = scan.nextInt(); // Set your desired raise amount
                pot += raiseAmount;
                player.setMoney(player.getMoney() - raiseAmount);
            } else if ("fold".equals(decision)) {
                pot += player.getMoney();
                player.setMoney(0);
            }
        }
        //ROUND 1
        burnPile.add(deck.deal());
        cardsInPlay.add(deck.deal());
    }
}