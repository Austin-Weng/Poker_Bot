
import java.util.*;
public class HarryBot3 extends Player {
    private Hand hand;

    public HarryBot3(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        return "fold";
    }

}