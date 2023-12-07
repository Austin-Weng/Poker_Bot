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
                for (Player person : orderOfPlay) {
                    if (person == austinBot) {
                        decisions[0] = austinBot.decision(cardsInPlay, pot, amountToPay);
                        System.out.println("Austin bot decision: " + decisions[0]);
                    } else if (person == harryBot) {
                        decisions[1] = harryBot.decision(cardsInPlay, pot, amountToPay);
                        System.out.println("Harry bot decision: " + decisions[1]);
                    } else {
                        decisions[2] = player.decision(cardsInPlay, pot, amountToPay);
                        System.out.println("Your decision: " + decisions[2]);
                    }
                }

                for (String decision : decisions) {
                    if ("check".equals(decision)) {
                        continue;
                    } else if ("raise".equals(decision)) {
                        int raiseAmount = new Scanner(System.in).nextInt();
                        pot += raiseAmount;
                        player.setMoney(player.getMoney() - raiseAmount);
                        amountToPay = raiseAmount;
                    } else if ("fold".equals(decision)) {
                        pot += amountToPay;
                        amountToPay = 0;
                    }
                }
            }
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
