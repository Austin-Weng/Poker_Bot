
import java.util.*;
public class HarryBot1 extends Player {
    private Hand hand;

    public HarryBot1(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        return "fold";
    }

}
