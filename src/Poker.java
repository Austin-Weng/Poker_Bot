import java.util.HashSet;

public class Poker {
    public static void playGame(Player[] orderOfPlay, HashSet<Card> cardsInPlay, Pot pot, Deck deck) {

        HashSet<Card> burnPile = new HashSet<>();
        String[] decisions = new String[8];

        // Deal initial cards to each bot
        for (Player bot : orderOfPlay) {
            bot.addCard(deck.deal());
            bot.addCard(deck.deal());
        }

        // Betting rounds
        for (int round = 1; round <= 3; round++) {

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
                } else {
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

            // Award the pot to the winner
            winner.setMoney(winner.getMoney() + pot);
        }
    }
    }

