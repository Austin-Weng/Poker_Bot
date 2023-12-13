import java.util.HashSet;
import java.util.Random;

public class HarryBot4 extends Player {
    private Hand hand;

    public HarryBot4(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet) {
        Random random = new Random();

        if (random.nextDouble() < 0.2) {
            // 20% chance to make a strategic bluff
            return "raise " + (currentBet.currentBet + random.nextInt(100));
        } else if (currentBet.currentBet == 0) {
            // If no one has raised, HarryBot2 may check or occasionally raise
            return (random.nextDouble() < 0.1) ? "raise " + random.nextInt(50) : "check";
        } else {
            // If someone has raised, HarryBot2 may call or occasionally fold
            return (random.nextDouble() < 0.1) ? "fold" : "call";
        }
    }
}
