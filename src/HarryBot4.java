
import java.util.*;
public class HarryBot4 extends Player {
    private Hand hand;

    public HarryBot4(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        if(pot == 0) {
            return "raise 200";
        }
        return "fold";
    }

}