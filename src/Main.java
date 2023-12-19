import java.util.*;
public class Main {
    public static void main(String[] args) {
        int austinBot1Wins = 0;
        int harryBot1Wins = 0;
        int austinBot2Wins = 0;
        int harryBot2Wins = 0;
        int austinBot3Wins = 0;
        int harryBot3Wins = 0;
        int austinBot4Wins = 0;
        int harryBot4Wins = 0;

        for (int game = 1; game <= 10; game++) {


            AustinBot1 austinBot1 = new AustinBot1(300);
            HarryBot1 harryBot1 = new HarryBot1(300);
            AustinBot2 austinBot2 = new AustinBot2(300);
            HarryBot2 harryBot2 = new HarryBot2(300);
            AustinBot3 austinBot3 = new AustinBot3(300);
            HarryBot3 harryBot3 = new HarryBot3(300);
            AustinBot4 austinBot4 = new AustinBot4(300);
            HarryBot4 harryBot4 = new HarryBot4(300);

            Player[] orderOfPlay = {austinBot1, harryBot1, austinBot2, harryBot2, austinBot3, harryBot3, austinBot4, harryBot4};
            HashSet<Card> cardsInPlay = new HashSet<>();
            Pot pot = new Pot(0);
            Deck deck = new Deck();
            deck.initializeDeck();
            deck.shuffle();

            Poker.playGame(orderOfPlay, cardsInPlay, pot, deck);
            checkGameWinner(orderOfPlay, game, austinBot1, harryBot1, austinBot2, harryBot2,
                    austinBot3, harryBot3, austinBot4, harryBot4, austinBot1Wins, harryBot1Wins,
                    austinBot2Wins, harryBot2Wins, austinBot3Wins, harryBot3Wins, austinBot4Wins, harryBot4Wins);
        }

        // Print results
        System.out.println("AustinBot1 Wins: " + austinBot1Wins);
        System.out.println("HarryBot1 Wins: " + harryBot1Wins);
        System.out.println("AustinBot2 Wins: " + austinBot2Wins);
        System.out.println("HarryBot2 Wins: " + harryBot2Wins);
        System.out.println("AustinBot3 Wins: " + austinBot3Wins);
        System.out.println("HarryBot3 Wins: " + harryBot3Wins);
        System.out.println("AustinBot4 Wins: " + austinBot4Wins);
        System.out.println("HarryBot4 Wins: " + harryBot4Wins);
    }

    private static void checkGameWinner(Player[] orderOfPlay, int game, Player austinBot1, Player harryBot1,
                                        Player austinBot2, Player harryBot2, Player austinBot3, Player harryBot3,
                                        Player austinBot4, Player harryBot4, int austinBot1Wins, int harryBot1Wins,
                                        int austinBot2Wins, int harryBot2Wins, int austinBot3Wins, int harryBot3Wins,
                                        int austinBot4Wins, int harryBot4Wins) {
        Player winner = null;
        int maxMoney = 0;

        // Find the player with the most money
        for (int i = 0; i < orderOfPlay.length; i++) {
            Player currentPlayer = orderOfPlay[i];
            if (currentPlayer != null && currentPlayer.getMoney() > maxMoney) {
                winner = currentPlayer;
                maxMoney = currentPlayer.getMoney();
            }
        }

        // If there is a winner, update the corresponding win count
        if (winner != null) {
            String winnerString = winner.toString();

            if (winnerString.contains("AustinBot1")) {
                austinBot1Wins++;
            } else if (winnerString.contains("HarryBot1")) {
                harryBot1Wins++;
            } else if (winnerString.contains("AustinBot2")) {
                austinBot2Wins++;
            } else if (winnerString.contains("HarryBot2")) {
                harryBot2Wins++;
            } else if (winnerString.contains("AustinBot3")) {
                austinBot3Wins++;
            } else if (winnerString.contains("HarryBot3")) {
                harryBot3Wins++;
            } else if (winnerString.contains("AustinBot4")) {
                austinBot4Wins++;
            } else if (winnerString.contains("HarryBot4")) {
                harryBot4Wins++;
            }

            System.out.println("Game " + game + " Winner: " + winnerString);
        }
    }

}