
import java.util.*;
public class HarryBot extends Player {
    private Hand hand;

    public HarryBot(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, int currentBet){
        if (currentBet == 0){
            return "check";
        } else {
            return "call";
        }
    }

}
