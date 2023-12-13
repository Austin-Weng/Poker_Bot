
import java.util.*;
public class HarryBot2 extends Player {
    private Hand hand;

    public HarryBot2(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        return "fold";
    }

}
