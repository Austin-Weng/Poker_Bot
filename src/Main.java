import java.util.*;
public class Main {
    public static void main(String[] args) {
        numWins austinBot1Wins = new numWins(0);
        numWins harryBot1Wins = new numWins(0);
        numWins austinBot2Wins = new numWins(0);
        numWins harryBot2Wins = new numWins(0);
        numWins austinBot3Wins = new numWins(0);
        numWins harryBot3Wins = new numWins(0);
        numWins austinBot4Wins = new numWins(0);
        numWins harryBot4Wins = new numWins(0);

        for (int game = 1; game <= 10; game++) {


            AustinBot1 austinBot1 = new AustinBot1(300);
            HarryBot1 harryBot1 = new HarryBot1(300);
            AustinBot1 austinBot2 = new AustinBot1(300);
            HarryBot2 harryBot2 = new HarryBot2(300);
            AustinBot1 austinBot3 = new AustinBot1(300);
            HarryBot3 harryBot3 = new HarryBot3(300);
            AustinBot1 austinBot4 = new AustinBot1(300);
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


        System.out.println("AustinBot1 Wins: " + austinBot1Wins.wins);
        System.out.println("HarryBot1 Wins: " + harryBot1Wins.wins);
        System.out.println("AustinBot2 Wins: " + austinBot2Wins.wins);
        System.out.println("HarryBot2 Wins: " + harryBot2Wins.wins);
        System.out.println("AustinBot3 Wins: " + austinBot3Wins.wins);
        System.out.println("HarryBot3 Wins: " + harryBot3Wins.wins);
        System.out.println("AustinBot4 Wins: " + austinBot4Wins.wins);
        System.out.println("HarryBot4 Wins: " + harryBot4Wins.wins);
    }

    private static void checkGameWinner(Player[] orderOfPlay, int game, Player austinBot1, Player harryBot1,
                                        Player austinBot2, Player harryBot2, Player austinBot3, Player harryBot3,
                                        Player austinBot4, Player harryBot4, numWins austinBot1Wins, numWins harryBot1Wins,
                                        numWins austinBot2Wins, numWins harryBot2Wins, numWins austinBot3Wins, numWins harryBot3Wins,
                                        numWins austinBot4Wins, numWins harryBot4Wins) {
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
                austinBot1Wins.wins++;
            } else if (winnerString.contains("HarryBot1")) {
                harryBot1Wins.wins++;
            } else if (winnerString.contains("AustinBot2")) {
                austinBot2Wins.wins++;
            } else if (winnerString.contains("HarryBot2")) {
                harryBot2Wins.wins++;
            } else if (winnerString.contains("AustinBot3")) {
                austinBot3Wins.wins++;
            } else if (winnerString.contains("HarryBot3")) {
                harryBot3Wins.wins++;
            } else if (winnerString.contains("AustinBot4")) {
                austinBot4Wins.wins++;
            } else if (winnerString.contains("HarryBot4")) {
                harryBot4Wins.wins++;
            }

            //System.out.println("Game " + game + " Winner: " + winnerString);
        }
    }

}