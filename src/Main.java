import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AustinBot1 austinBot1 = new AustinBot1(300);
        AustinBot1 harryBot1 = new AustinBot1(300);
        AustinBot1 austinBot2 = new AustinBot1(300);
        AustinBot1 harryBot2 = new AustinBot1(300);
        AustinBot1 austinBot3 = new AustinBot1(300);
        AustinBot1 harryBot3 = new AustinBot1(300);
        AustinBot1 austinBot4 = new AustinBot1(300);
        AustinBot1 harryBot4 = new AustinBot1(300);

        HashSet<Card> cardsInPlay = new HashSet<>();
        HashSet<Card> burnPile = new HashSet<>();
        Pot pot = new Pot(0);
        Deck deck = new Deck();
        deck.initializeDeck();
        deck.shuffle();

        Player[] orderOfPlay = {austinBot1, harryBot1, austinBot2, harryBot2, austinBot3, harryBot3, austinBot4, harryBot4};
        HashMap<Player, Integer> wins = new HashMap<>();
        for (Player p : orderOfPlay){
            wins.put(p, 0);
        }
        String[] decisions = new String[8];

        // Deal initial cards to each bot
        for (Player bot : orderOfPlay) {
            bot.addCard(deck.deal());
            bot.addCard(deck.deal());
        }

        // Betting rounds
        for (int i = 0; i < 1000; i++) {
            while(isWinner(orderOfPlay) == null){
                for (int round = 1; round <= 3; round++) {
                    //System.out.println("ROUND " + round);

                    // Reveal three community cards in the first round
                    if (round == 1) {
                        burnPile.add(deck.deal());
                        cardsInPlay.add(deck.deal());
                        cardsInPlay.add(deck.deal());
                        cardsInPlay.add(deck.deal());
                    } else {
                        // Add one community card in rounds 2 and 3
                        burnPile.add(deck.deal());
                        cardsInPlay.add(deck.deal());
                    }

                    //System.out.println("Community Cards: " + cardsInPlay);

                    bettingRound(decisions, orderOfPlay, cardsInPlay, pot, deck);
                }


                checkWinner(orderOfPlay, pot.pot);
            }
            wins.replace(isWinner(orderOfPlay), wins.get(isWinner(orderOfPlay)) + 1);
        }
        int mostWins = 0;
        Player pMostWins = null;
        for (Player p : wins.keySet()){
            if (wins.get(p) > mostWins){
                mostWins = wins.get(p);
                pMostWins = p;
            }
        }
        assert pMostWins != null;
        pMostWins.variables();
    }

    public static void bettingRound(String[] decisions, Player[] orderOfPlay, HashSet<Card> cardsInPlay, Pot pot, Deck deck) {
        CurrentBet currentBet = new CurrentBet(0);
        int consecutiveCalls = 0;

        while (countActivePlayers(orderOfPlay) > 1) {
            boolean allCalled = true;
            for (int i = 0; i < orderOfPlay.length; i++) {
                Player player = orderOfPlay[i];
                if (player.getMoney() <= 0) {
                    player.setBroke(true);
                }
                if (player != null && !player.hasFolded() && !player.getIsBroke()) {
                    decisions[i] = makeDecision(player, orderOfPlay, cardsInPlay, pot, currentBet);
                    //System.out.println(player + " decision: " + decisions[i]);

                    if (!decisions[i].equals("call")) {
                        allCalled = false;
                    }
                } else {
                    //System.out.println(player + " has folded/isBroke. Skipping player's turn.");
                    decisions[i] = "fold";
                }
            }

            // If all players called, break the loop
            if (allCalled) {
                consecutiveCalls++;
                if (consecutiveCalls >= countActivePlayers(orderOfPlay)) {
                    break;
                }
            } else {
                consecutiveCalls = 0;
            }

            // If only one player is active, end the round
            if (countActivePlayers(orderOfPlay) == 1) {
                break;
            }
        }
    }


    private static int countActivePlayers(Player[] orderOfPlay) {
        int activePlayers = 0;
        for (Player player : orderOfPlay) {
            if (player != null && !player.hasFolded()) {
                activePlayers++;
            }
        }
        return activePlayers;
    }


    // Modify makeDecision method to set player's folded status
    public static String makeDecision(Player player, Player[] orderOfPlay, HashSet<Card> cardsInPlay, Pot pot, CurrentBet currentBet) {
        if (!player.hasFolded()) { // Skip folded players
            String decision = player.decision(cardsInPlay, pot.pot, currentBet);
            if (decision.equals("fold")) {
                player.setFolded(true);
            } else if (decision.startsWith("raise")) {
                int raiseAmount = Integer.parseInt(decision.split(" ")[1]);
                pot.pot += raiseAmount;
                currentBet.currentBet += raiseAmount;
            } else if (decision.equals("call")) {
                pot.pot += currentBet.currentBet;
            }
            return decision;
        } else {
            return "fold";
        }
    }


    public static boolean isRoundOver(String[] decisions) {
        for (String decision : decisions) {
            if (decision != null && decision.startsWith("raise")) {
                return false;
            }
        }
        return true;
    }

    public static void checkWinner(Player[] orderOfPlay, int pot) {
        Player winner = null;
        int maxRank = 0;

        // Find the remaining player (the one who has not folded)
        for (int i = 0; i < orderOfPlay.length; i++) {
            if (orderOfPlay[i] != null && !orderOfPlay[i].hasFolded()) {
                winner = orderOfPlay[i];
                break;
            }
        }

        // If there is a winner, print their hand and the amount won
        if (winner != null) {
            //System.out.println("Winner: " + winner);
            //System.out.println("Winner's Hand: " + winner.getHand());
            //System.out.println("Amount won: " + pot);

            // Award the pot to the winner
            winner.setMoney(winner.getMoney() + pot);
        }
    }

    public static Player isWinner(Player[] orderOfPlay){
        int brokeCount = 0;
        Player winner = null;
        for (Player p : orderOfPlay) {
            if (!p.getIsBroke()){
                winner = p;
            } else {
                brokeCount++;
            }
        }
        if (brokeCount == orderOfPlay.length-1){
            return winner;
        } else {
            return null;
        }
    }

}
