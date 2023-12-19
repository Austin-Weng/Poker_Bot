import java.util.HashSet;
import java.util.Random;

public class HarryBot1 extends Player {
    public HarryBot1(int money) {
        this.setMoney(money);
    }
    public String toString() {
        return "HarryBot1";
    }

    @Override
    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet) {
        Random random = new Random();
        if (random.nextDouble() < 0.2) {
            int maxRaise = Math.min(300, getMoney()); // Limit raise to 300 or available money
            return "raise " + (currentBet.currentBet + random.nextInt(maxRaise));
        } else {
            return (random.nextDouble() < 0.1) ? "fold" : "call";
        }
    }
}
