import java.util.*;
import java.util.concurrent.TimeUnit;
public class Main {
    public static void main(String[] args) throws InterruptedException {
        AustinBot austinBot = new AustinBot(300);
        HarryBot harryBot = new HarryBot(300);
        HashSet<Card> cardsInPlay = new HashSet<Card>();
        HashSet<Card> burnPile = new HashSet<Card>();
        int pot = 0;
        Deck deck = new Deck();
        deck.initializeDeck();
        deck.shuffle();

        Player player = new Player(300);
        Player[] orderOfPlay = {austinBot, harryBot, player};
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
                decisions[2] = playerDecision;
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
        System.out.println(cardsInPlay);

        System.out.println("Austin bot decision: " + austinBot.decision(cardsInPlay, pot));
        System.out.println("Harry bot decision: " + harryBot.decision(cardsInPlay, pot));
        System.out.println("What would you like to do? (fold/check/call/raise)");
        scan = new Scanner(System.in);
        playerDecision = scan.next();

        for (String person : orderOfPlay) {
            if (person.equals("austin")) {
                decisions[0] = austinBot.decision(cardsInPlay, pot);
                System.out.println("Austin bot decision: " + decisions[0]);
            } else if (person.equals("harry")) {
                decisions[1] = harryBot.decision(cardsInPlay, pot);
                System.out.println("Harry bot decision: " + decisions[1]);
            } else {
                decisions[2] = playerDecision;
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


        //Round 3: turn
        //Round 4: river
        //take decisions

        if (decisions[0].equals("check") && decisions[1].equals("check") && decisions[2].equals("check")){
            if (checkForRoundEnd(harryBot.decision(cardsInPlay, pot), scan.next())){
                Player max = new Player();
                max.
                for (Player x : orderOfPlay){
                    if (x.getHand().handRank() > max.getHand().handRank()){
                        max = x;
                    }
                }
                System.out.println("Winner of the pot...");
                for(int i = 0; i < 3; i++){
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("...");
                }
                System.out.println(max + "! Amount won: " + pot);
                max.setMoney(max.getMoney() + pot);
                //restart

            }
        }

    }

    public static boolean checkForRoundEnd(String decision1, String decision2){
        return !decision1.equals("raise") && !decision2.equals("raise");
    }

}