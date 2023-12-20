import java.util.HashSet;

public class HarryBot3 extends Player {
    private int roundsPlayed = 0;
    private int totalRaises = 0;
    private int totalCalls = 0;
    private int bluffRounds = 0;
    private int bluffCount = 0;
    private int allInRounds = 0;
    private int allInCount = 0;

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

        // Adjust probability of raising based on hand strength, aggression, and bluffing
        double raiseProbability = Math.min(0.2 + getHand().handRank() * 0.1 - aggressionRatio * 0.05, 0.8);

        // Introduce occasional bluffing
        if (roundsPlayed > 3 && bluffCount < 2 && Math.random() < 0.2) {
            bluffRounds++;
            bluffCount++;
            int maxRaise = Math.min(currentBet.currentBet + 50, Math.min(getMoney(), 100)); // Limit bluff raise to 100 or available money
            return "raise " + (currentBet.currentBet + (int) (Math.random() * maxRaise));
        }

        // Introduce occasional all-in moves
        if (roundsPlayed > 5 && allInCount < 2 && Math.random() < 0.2) {
            allInRounds++;
            allInCount++;
            return "all-in";
        }

        // Make decisions based on probabilities
        double decisionProbability = Math.random();
        if (decisionProbability < foldProbability) {
            return "fold";
        } else if (decisionProbability < foldProbability + raiseProbability) {
            int maxRaise = Math.min(currentBet.currentBet + 100, Math.min(getMoney(), 300)); // Limit raise to 300 or available money
            return "raise " + (currentBet.currentBet + (int) (Math.random() * maxRaise));
        } else {
            // Ensure it doesn't call above its current money
            int maxCall = Math.min(currentBet.currentBet, getMoney());
            return "call " + (int) (Math.random() * maxCall);
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
