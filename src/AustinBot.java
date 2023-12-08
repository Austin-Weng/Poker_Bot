import java.util.HashSet;
import java.util.LinkedList;

public class AustinBot extends Player{
    private Hand hand;
    public AustinBot(int money) {
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
