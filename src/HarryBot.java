
import java.util.*;
public class HarryBot extends Player {
    private Hand hand;

    public HarryBot(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        if (currentBet.currentBet == 0){
            return "check";
        } else {
            return "call";
        }
    }

}
