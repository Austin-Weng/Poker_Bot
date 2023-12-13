import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AustinBot1 austinBot1 = new AustinBot1(300);
        HarryBot1 harryBot1 = new HarryBot1(300);
        AustinBot2 austinBot2 = new AustinBot2(300);
        HarryBot2 harryBot2 = new HarryBot2(300);
        AustinBot3 austinBot3 = new AustinBot3(300);
        HarryBot3 harryBot3 = new HarryBot3(300);
        AustinBot4 austinBot4 = new AustinBot4(300);
        HarryBot4 harryBot4 = new HarryBot4(300);

        HashSet<Card> cardsInPlay = new HashSet<>();
        HashSet<Card> burnPile = new HashSet<>();
        Pot pot = new Pot(0);
        Deck deck = new Deck();
        deck.initializeDeck();
        deck.shuffle();

        Player[] orderOfPlay = {austinBot1, harryBot1, austinBot2, harryBot2, austinBot3, harryBot3, austinBot4, harryBot4};
        String[] decisions = new String[8];

        // Deal initial cards to each bot
        for (Player bot : orderOfPlay) {
            bot.addCard(deck.deal());
            bot.addCard(deck.deal());
        }

        // Betting rounds
        for (int round = 1; round <= 3; round++) {
            System.out.println("ROUND " + round);

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

            System.out.println("Community Cards: " + cardsInPlay);

            bettingRound(decisions, orderOfPlay, cardsInPlay, pot, deck);
        }


        checkWinner(orderOfPlay, pot.pot);
    }

    public static void bettingRound(String[] decisions, Player[] orderOfPlay, HashSet<Card> cardsInPlay, Pot pot, Deck deck) {
        CurrentBet currentBet = new CurrentBet(0);

        while (countActivePlayers(orderOfPlay) > 1) {
            for (int i = 0; i < orderOfPlay.length; i++) {
                Player player = orderOfPlay[i];
                if (player != null && !player.hasFolded()) {
                    decisions[i] = makeDecision(player, orderOfPlay, cardsInPlay, pot, currentBet);
                    System.out.println(player + " decision: " + decisions[i]);
                } else {
                    System.out.println(player + " has folded. Skipping player's turn.");
                    // If the player has folded, set their decision to "fold" and skip their turn
                    decisions[i] = "fold";
                }
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
            System.out.println("Winner: " + winner);
            System.out.println("Winner's Hand: " + winner.getHand());
            System.out.println("Amount won: " + pot);

            // Award the pot to the winner
            winner.setMoney(winner.getMoney() + pot);
        }
    }

}
