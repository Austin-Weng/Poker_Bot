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

        for (int game = 1; game <= 50000; game++) {


            AustinBot1 austinBot1 = new AustinBot1(100);
            HarryBot1 harryBot1 = new HarryBot1(100);
            AustinBot2 austinBot2 = new AustinBot2(100);
            HarryBot2 harryBot2 = new HarryBot2(100);
            AustinBot3 austinBot3 = new AustinBot3(100);
            HarryBot3 harryBot3 = new HarryBot3(100);
            AustinBot4 austinBot4 = new AustinBot4(100);
            HarryBot4 harryBot4 = new HarryBot4(100);

            Player[] orderOfPlay = {austinBot1, harryBot1, austinBot2, harryBot4, austinBot3, harryBot3, austinBot4, harryBot2};
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

    private static void checkGameWinner(Player[] orderOfPlay, int game,
                                        Player austinBot1, Player harryBot1,
                                        Player austinBot2, Player harryBot2,
                                        Player austinBot3, Player harryBot3,
                                        Player austinBot4, Player harryBot4,
                                        numWins austinBot1Wins, numWins harryBot1Wins,
                                        numWins austinBot2Wins, numWins harryBot2Wins,
                                        numWins austinBot3Wins, numWins harryBot3Wins,
                                        numWins austinBot4Wins, numWins harryBot4Wins) {
        Player winner = null;

        // Find the last player who hasn't folded
        for (int i = orderOfPlay.length - 1; i >= 0; i--) {
            Player currentPlayer = orderOfPlay[i];
            if (currentPlayer != null && !currentPlayer.hasFolded()) {
                winner = currentPlayer;
                break;
            }
        }

        // If all players have folded, set the winner to the last player who folded
        if (winner == null) {
            for (int i = orderOfPlay.length - 1; i >= 0; i--) {
                Player currentPlayer = orderOfPlay[i];
                if (currentPlayer != null) {
                    winner = currentPlayer;
                    break;
                }
            }
        }

        // If there is a winner, update the corresponding win count
        if (winner != null) {
            String winnerString = winner.toString();

            if (winnerString.contains(austinBot1.toString())) {
                austinBot1Wins.wins++;
            } else if (winnerString.contains(harryBot1.toString())) {
                harryBot1Wins.wins++;
            } else if (winnerString.contains(austinBot2.toString())) {
                austinBot2Wins.wins++;
            } else if (winnerString.contains(harryBot2.toString())) {
                harryBot2Wins.wins++;
            } else if (winnerString.contains(austinBot3.toString())) {
                austinBot3Wins.wins++;
            } else if (winnerString.contains(harryBot3.toString())) {
                harryBot3Wins.wins++;
            } else if (winnerString.contains(austinBot4.toString())) {
                austinBot4Wins.wins++;
            } else if (winnerString.contains(harryBot4.toString())) {
                harryBot4Wins.wins++;
            }
        }
    }


}