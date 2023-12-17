import java.util.HashSet;

public class HarryBot2 extends Player {
    private int totalRaises = 0;
    private int totalCalls = 0;
    private int totalFolds = 0;

    public HarryBot2(int money) {
        this.setMoney(money);
    }

    @Override
    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet) {
        // Assess opponent aggression based on raise/call/fold ratio
        double aggressionRatio = calculateAggressionRatio();

        // Adjust probability of folding based on aggression
        double foldProbability = Math.min(0.1 + aggressionRatio * 0.2, 0.5);

        // Adjust probability of raising based on hand strength, aggression, and pot size
        double raiseProbability = Math.min(0.2 + getHand().handRank() * 0.1 - aggressionRatio * 0.05 +
                pot * 0.0001, 0.8);

        // Limit raise to 300 or available money
        int maxRaise = Math.min(300, getMoney());

        // Make decisions based on probabilities
        double decisionProbability = Math.random();
        if (decisionProbability < foldProbability) {
            totalFolds++;
            return "fold";
        } else if (decisionProbability < foldProbability + raiseProbability) {
            totalRaises++;
            return "raise " + (currentBet.currentBet + (int) (Math.random() * maxRaise));
        } else {
            totalCalls++;
            return "call";
        }
    }

    private double calculateAggressionRatio() {
        // Avoid division by zero
        if (totalCalls == 0) {
            return 1.0;
        }

        return (double) (totalRaises + totalFolds) / totalCalls;
    }
}
