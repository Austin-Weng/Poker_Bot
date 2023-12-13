import java.util.HashSet;
import java.util.Random;

public class HarryBot1 extends Player {
    public HarryBot1(int money) {
        super(money);
    }

    @Override
    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet) {
        int handRank = getHand().handRank();

        // Introduce some randomness
        Random random = new Random();

        // Adjust these conditions based on your strategy
        if (currentBet.currentBet == 0) {
            // Only make "check," "raise," or "fold" decision in the first round
            if (handRank >= 8 || (random.nextInt(10) >= 8 && handRank >= 5)) {
                // Raise if hand rank is high or randomly determined and hand rank is decent
                int randomRaise = Math.max(1, random.nextInt(300));
                return "raise " + randomRaise;
            } else {
                // Check or fold based on hand rank and randomness
                return (handRank >= 5 || random.nextInt(10) >= 5) ? "check" : "fold";
            }
        } else {
            // In subsequent rounds, follow the previous logic
            if (handRank >= 8 || (random.nextInt(10) >= 8 && handRank >= 5)) {
                // Raise if hand rank is high or randomly determined and hand rank is decent
                int randomRaise = Math.max(1, random.nextInt(300));
                return "raise " + randomRaise;
            } else if (handRank >= 5 && currentBet.currentBet < 50) {
                // Call if hand rank is decent and the current bet is reasonable
                return "call";
            } else if (currentBet.currentBet == 0) {
                // Check if no bet has been made
                return "check";
            } else {
                // Fold in other situations
                return "fold";
            }
        }
    }
}
