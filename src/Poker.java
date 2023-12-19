import java.util.HashSet;

public class Poker {
    public static void playGame(Player[] orderOfPlay, HashSet<Card> cardsInPlay, Pot pot, Deck deck) {
        String[] decisions = new String[8];

        // Deal initial cards to each bot
        for (Player bot : orderOfPlay) {
            bot.addCard(deck.deal());
            bot.addCard(deck.deal());
        }

        // Betting rounds
        for (int round = 1; round <= 3; round++) {

            // Reveal community cards
            revealCommunityCards(cardsInPlay, deck, round);

            bettingRound(decisions, orderOfPlay, cardsInPlay, pot, deck);
        }

        checkWinner(orderOfPlay, pot.pot);
    }

    private static void revealCommunityCards(HashSet<Card> cardsInPlay, Deck deck, int round) {
        int cardsToAdd = (round == 1) ? 3 : 1;
        for (int i = 0; i < cardsToAdd; i++) {
            deck.deal(); // Burn a card
            cardsInPlay.add(deck.deal());
        }
    }

    private static void bettingRound(String[] decisions, Player[] orderOfPlay, HashSet<Card> cardsInPlay, Pot pot, Deck deck) {
        CurrentBet currentBet = new CurrentBet(0);

        while (countActivePlayers(orderOfPlay) > 1 && !isRoundOver(decisions)) {
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

    private static String makeDecision(Player player, Player[] orderOfPlay, HashSet<Card> cardsInPlay, Pot pot, CurrentBet currentBet) {
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

    private static boolean isRoundOver(String[] decisions) {
        for (String decision : decisions) {
            if (decision != null && decision.startsWith("raise")) {
                return false;
            }
        }
        return true;
    }

    private static void checkWinner(Player[] orderOfPlay, int pot) {
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

            // Award the pot to the winner
            winner.setMoney(winner.getMoney() + pot);
        }
    }
}