import java.util.HashSet;
import java.util.Random;

public class HarryBot4 extends Player {
    public HarryBot4(int money) {
        this.setMoney(money);
    }
    public String toString() {
        return "HarryBot4";
    }
    @Override
    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet) {
        Random random = new Random();

        int handRank = getHand().handRank();
        double raiseProbability = 0.2;

        if (handRank >= 7) {
            raiseProbability = 0.8;
        } else if (handRank >= 5) {
            raiseProbability = 0.5;
        }

        if (random.nextDouble() < raiseProbability) {
            int maxRaise = Math.min(currentBet.currentBet + 100, Math.min(getMoney(), 300));
            maxRaise = Math.max(1, maxRaise); // Ensure that maxRaise is at least 1
            return "raise " + (currentBet.currentBet + random.nextInt(maxRaise));
        } else if (currentBet.currentBet == 0) {
            return (random.nextDouble() < 0.1) ? "raise " + (random.nextInt(49) + 1) : "check";
        } else {
            return (random.nextDouble() < 0.2) ? "fold" : "call";
        }
    }

}
