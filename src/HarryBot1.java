import java.util.HashSet;
import java.util.Random;

public class HarryBot1 extends Player {
    public HarryBot1(int money) {
        this.setMoney(money);
    }

    @Override
    public String toString() {
        return "HarryBot1";
    }

    @Override
    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet) {
        Random random = new Random();

        int handRank = evaluateHand(cardsInPlay);
        double potOdds = calculatePotOdds(pot, currentBet.currentBet);

        // Bluffing with draws strategy
        if (handRank >= 6 && random.nextDouble() < 0.5) {
            int maxRaise = Math.min(100, getMoney());
            return "raise " + (currentBet.currentBet + random.nextInt(maxRaise));
        } else {
            // Bluff with draws (straight draws, flush draws, or overcards)
            boolean bluffWithDraw = random.nextDouble() < 0.3; // Adjust the bluff frequency as needed

            if (bluffWithDraw) {
                int maxRaise = Math.min(50, getMoney());
                return "raise " + (currentBet.currentBet + random.nextInt(maxRaise));
            } else {
                // Check if it's possible to call, otherwise, fold
                return (currentBet.currentBet <= getMoney() && random.nextDouble() < potOdds) ? "call" : "fold";
            }
        }
    }

    private int evaluateHand(HashSet<Card> cardsInPlay) {
        // Simple hand evaluation: just count the number of cards in the hand
        return cardsInPlay.size();
    }

    private double calculatePotOdds(int pot, int currentBet) {
        // Calculate pot odds to determine the attractiveness of the current bet
        int totalPot = pot + currentBet;
        return (double) currentBet / totalPot * 100;
    }
}
