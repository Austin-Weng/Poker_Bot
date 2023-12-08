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
        burnPile.add(deck.deal());
        cardsInPlay.add(deck.deal());
        cardsInPlay.add(deck.deal());
        System.out.println("Game initialized");
        System.out.println(cardsInPlay);

        // Betting rounds
        for (int round = 0; round < 4; round++) {
            if (round == 1) {
                System.out.println("ROUND 1");
            } else if (round == 3) {
                System.out.println("ROUND 2: TURN");
            } else if (round == 4) {
                System.out.println("ROUND 3: RIVER");
            }

            burnPile.add(deck.deal());
            cardsInPlay.add(deck.deal());
            System.out.println(cardsInPlay);


            while (checkForRoundEnd(decisions[0], decisions[1])) {

                String playerDecision;


                for (Player person : orderOfPlay) {
                    if (person == austinBot && (decisions[0] == null || !decisions[0].equals("fold"))) {
                        decisions[0] = austinBot.decision(cardsInPlay, pot, 0);
                        System.out.println("Austin bot decision: " + decisions[0]);
                    } else if (person == harryBot && (decisions[1] == null || !decisions[1].equals("fold"))) {
                        decisions[1] = harryBot.decision(cardsInPlay, pot, 0);
                        System.out.println("Harry bot decision: " + decisions[1]);
                    } else if (person == player) {
                        if (decisions[2] != null && decisions[2].equals("fold")) {
                            break;
                        }
                        playerDecision = player.decision(cardsInPlay, pot, 0);
                        decisions[2] = playerDecision;
                        System.out.println("Your decision: " + decisions[2]);
                    }
                }




                for (String decision : decisions) {
                    if ("check".equals(decision)) {
                        Card newCard = deck.deal();
                        System.out.println("New card revealed: " + newCard);
                        cardsInPlay.add(newCard);
                    } else if (decision.startsWith("raise")) {
                        int raiseAmount = Integer.parseInt(decision.split(" ")[1]);
                        pot += raiseAmount;
                        amountToPay = raiseAmount;
                        player.setMoney(player.getMoney() - raiseAmount);
                    } else if ("fold".equals(decision)) {
                        pot += amountToPay;
                        amountToPay = 0;
                    }
                }
            }

// ... existing code ...

        }

        if (checkForRoundEnd(decisions[0], decisions[1]) && decisions[2].equals("check")) {
            Player winner = null;
            int maxRank = 0;

            for (Player person : orderOfPlay) {
                int rank = person.getHand().handRank();
                if (rank > maxRank) {
                    winner = person;
                    maxRank = rank;
                }
            }

            System.out.println("Winner of the pot: " + winner + "! Amount won: " + pot);
            winner.setMoney(winner.getMoney() + pot);

        }
    }

    public static boolean checkForRoundEnd(String decision1, String decision2) {
        return !"raise".equals(decision1) && !"raise".equals(decision2);
    }

}
