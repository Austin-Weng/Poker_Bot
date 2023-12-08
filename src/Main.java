import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AustinBot austinBot = new AustinBot(300);
        HarryBot harryBot = new HarryBot(300);
        HashSet<Card> cardsInPlay = new HashSet<>();
        HashSet<Card> burnPile = new HashSet<>();
        int pot = 0;
        Deck deck = new Deck();
        deck.initializeDeck();
        deck.shuffle();
        Player player = new Player(300);
        Player[] orderOfPlay = {austinBot, harryBot, player};
        String[] decisions = new String[3];
        int amountToPay = 0;
        for (Player person : orderOfPlay) {
            person.addCard(deck.deal());
            person.addCard(deck.deal());
        }

        // Betting rounds
        System.out.println("ROUND 1");
        burnPile.add(deck.deal());
        cardsInPlay.add(deck.deal());
        cardsInPlay.add(deck.deal());
        cardsInPlay.add(deck.deal());
        System.out.println("Cards In Play" + cardsInPlay);
        bettingRound(decisions, orderOfPlay, cardsInPlay, pot, deck, austinBot, harryBot, player);
        System.out.println("ROUND 2: TURN");
        burnPile.add(deck.deal());
        cardsInPlay.add(deck.deal());
        System.out.println("Cards In Play" + cardsInPlay);
        bettingRound(decisions, orderOfPlay, cardsInPlay, pot, deck, austinBot, harryBot, player);
        System.out.println("ROUND 3: RIVER");
        burnPile.add(deck.deal());
        cardsInPlay.add(deck.deal());
        System.out.println("Cards In Play" + cardsInPlay);
        bettingRound(decisions, orderOfPlay, cardsInPlay, pot, deck, austinBot, harryBot, player);
        checkWinner(orderOfPlay, pot);

    }

    public static void bettingRound(String[] decisions, Player[] orderOfPlay, HashSet<Card> cardsInPlay, int pot, Deck deck, Player austinBot, Player harryBot, Player player) {
        int currentBet = 0;
        boolean playerFolded = false;

        // First round
        decisions[0] = makeDecision(austinBot, orderOfPlay, cardsInPlay, pot, currentBet);
        System.out.println("Austin bot decision: " + decisions[0]);
        decisions[1] = makeDecision(harryBot, orderOfPlay, cardsInPlay, pot, currentBet);
        System.out.println("Harry bot decision: " + decisions[1]);
        if (orderOfPlay[2] != null) {
            decisions[2] = makeDecision(player, orderOfPlay, cardsInPlay, pot, currentBet);
            System.out.println("Your decision: " + decisions[2]);
        } else {
            playerFolded = true;
            System.out.println("Player has folded. Skipping player's turn.");
        }

        while (!isRoundOver(decisions[0], decisions[1], decisions[2])) {
            decisions[0] = null;
            decisions[1] = null;
            decisions[2] = null;
            currentBet = 0;

            for (int i = 0; i < 3; i++) {
                if (orderOfPlay[i] != null && !playerFolded) {
                    decisions[i] = makeDecision(orderOfPlay[i], orderOfPlay, cardsInPlay, pot, currentBet);
                    System.out.println(orderOfPlay[i] + " decision: " + decisions[i]);
                }
            }

        }
    }


    public static String makeDecision(Player player, Player[] orderOfPlay, HashSet<Card> cardsInPlay, int pot, int currentBet) {
        if (player != null) { // Skip folded players
            String decision = player.decision(cardsInPlay, pot, currentBet);
            if (decision.startsWith("raise")) {
                int raiseAmount = Integer.parseInt(decision.split(" ")[1]);
                pot += raiseAmount;
                currentBet += raiseAmount;
            } else if (decision.equals("call")) {
                pot += currentBet;
            } else if (decision.equals("fold")) {
                for (int i = 0; i < orderOfPlay.length; i++) {
                    if (player == orderOfPlay[i]) {
                        orderOfPlay[i] = null;
                    }
                }
            }
            return decision;
        }
        return "fold";
    }



    public static int activePlayers(Player[] players) {
        int count = 0;
        for (Player player : players) {
            if (player != null) {
                count++;
            }
        }
        return count;
    }
    public static boolean isRoundOver(String decision0, String decision1, String decision2) {
//        System.out.println("check for round end, decision1: " + decision1 + " decision2: " + decision2);
//        System.out.println(!"raise".equals(decision1) && !"raise".equals(decision2));
        return !decision1.startsWith("raise") && !decision2.startsWith("raise");
    }



    public static void checkWinner(Player[] orderOfPlay, int pot){
        Player winner = null;
        int maxRank = 0;

        for (int i = 0; i < 3; i++) {
            if (orderOfPlay[i] != null){
                int rank = orderOfPlay[i].getHand().handRank();
                if (rank > maxRank) {
                    winner = orderOfPlay[i];
                    maxRank = rank;
                }
            }
        }

        System.out.println("Winner of the pot: " + winner + "! Amount won: " + pot);
        winner.setMoney(winner.getMoney() + pot);
    }
}
//loop through the order of play
//            for (Player person : orderOfPlay) {
//                if (person == austinBot && (decisions[0] == null || !decisions[0].equals("fold"))) {
//                    decisions[0] = austinBot.decision(cardsInPlay, pot, 0);
//                    System.out.println("Austin bot decision: " + decisions[0]);
//                } else if (person == harryBot && (decisions[1] == null || !decisions[1].equals("fold"))) {
//                    decisions[1] = harryBot.decision(cardsInPlay, pot, 0);
//                    System.out.println("Harry bot decision: " + decisions[1]);
//                } else if (person == player) {
//                    if (decisions[2] != null && decisions[2].equals("fold")) {
//                        break;
//                    }
//                    playerDecision = player.decision(cardsInPlay, pot, 0);
//                    decisions[2] = playerDecision;
//                    System.out.println("Your decision: " + decisions[2]);
//                }
//            }
//
//            for (String decision : decisions) {
//                int amountToPay = 0;
//                if ("check".equals(decision)) {
//                    Card newCard = deck.deal();
//                    System.out.println("New card revealed: " + newCard);
//                    cardsInPlay.add(newCard);
//                } else if (decision.startsWith("raise")) {
//                    int raiseAmount = Integer.parseInt(decision.split(" ")[1]);
//                    pot += raiseAmount;
//                    amountToPay = raiseAmount;
//                    player.setMoney(player.getMoney() - raiseAmount);
//                } else if ("fold".equals(decision)) {
//                    pot += amountToPay;
//                    amountToPay = 0;
//                }
//            }