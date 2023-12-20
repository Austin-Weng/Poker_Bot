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
        int handRank = evaluateHand(cardsInPlay);
        int potOdds = calculatePotOdds(pot, currentBet.currentBet);
        double aggressionRatio = calculateAggressionRatio();
        double opponentHandStrength = calculateOpponentHandStrength(cardsInPlay);
        double foldProbability = calculateFoldProbability(aggressionRatio, opponentHandStrength);
        double raiseProbability = calculateRaiseProbability(handRank, aggressionRatio, potOdds);
        int maxRaise = Math.min(100, getMoney());

        // Implement push-fold strategy when the stack is low
        if (shouldUsePushFoldStrategy()) {
            return "all-in";
        }

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

    private boolean shouldUsePushFoldStrategy() {
        // Determine whether to switch to push-fold strategy based on certain conditions
        // For example, when the stack is low or in specific game situations
        return getMoney() <= 10;
    }

    // Other methods remain unchanged...

    private int evaluateHand(HashSet<Card> cardsInPlay) {
        // Simple hand evaluation: just count the number of cards in the hand
        return cardsInPlay.size();
    }

    private double calculateFoldProbability(double aggressionRatio, double opponentHandStrength) {
        // Adjusted fold probability based on aggression and opponent's hand strength
        return Math.min(0.1 + aggressionRatio * 0.2 + opponentHandStrength * 0.1, 0.5);
    }

    private double calculateRaiseProbability(int handRank, double aggressionRatio, int potOdds) {
        // Adjusted raise probability based on hand strength, aggression, and pot odds
        return Math.min(0.2 + handRank * 0.1 - aggressionRatio * 0.05 + potOdds * 0.0001, 0.8);
    }

    private int calculatePotOdds(int pot, int currentBet) {
        // Calculate pot odds to determine the attractiveness of the current bet
        int totalPot = pot + currentBet;
        return (int) ((double) currentBet / totalPot * 100);
    }

    private double calculateAggressionRatio() {
        // Avoid division by zero
        if (totalCalls == 0) {
            return 1.0;
        }
        return (double) (totalRaises + totalFolds) / totalCalls;
    }

    private double calculateOpponentHandStrength(HashSet<Card> cardsInPlay) {
        // Simple estimation: just use the size of the opponent's hand
        int opponentHandRank = evaluateHand(cardsInPlay);
        return opponentHandRank / 7.0;
    }
}
