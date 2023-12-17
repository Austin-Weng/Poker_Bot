import java.util.HashSet;

public class HarryBot3 extends Player {
    private int roundsPlayed = 0;
    private int totalRaises = 0;
    private int totalCalls = 0;

    public HarryBot3(int money) {
        this.setMoney(money);
    }

    @Override
    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet) {
        roundsPlayed++;

        // Assess opponent aggression based on raise/call ratio
        double aggressionRatio = calculateAggressionRatio();

        // Adjust probability of folding based on aggression
        double foldProbability = Math.min(0.3 + aggressionRatio * 0.1, 0.8);

        // Adjust probability of raising based on hand strength and aggression
        double raiseProbability = Math.min(0.2 + getHand().handRank() * 0.1 - aggressionRatio * 0.05, 0.8);

        // Make decisions based on probabilities
        double decisionProbability = Math.random();
        if (decisionProbability < foldProbability) {
            return "fold";
        } else if (decisionProbability < foldProbability + raiseProbability) {
            int maxRaise = Math.min(currentBet.currentBet + 100, Math.min(getMoney(), 300)); // Limit raise to 300 or available money
            return "raise " + (currentBet.currentBet + (int) (Math.random() * maxRaise));
        } else {
            return "call";
        }
    }

    private double calculateAggressionRatio() {
        // Avoid division by zero
        if (totalCalls == 0) {
            return 1.0;
        }

        return (double) totalRaises / totalCalls;
    }

    public void updateStatistics(String decision) {
        if (decision.startsWith("raise")) {
            totalRaises++;
        } else if (decision.equals("call")) {
            totalCalls++;
        }
    }
}
