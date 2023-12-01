import java.util.HashSet;
import java.util.LinkedList;

public class AustinBot extends Player{
    private HashSet<Card> hand;

    public AustinBot(int money) {
        this.setMoney(money);
    }

    public String decision(LinkedList<Card> cardsInPlay, int pot){
        return "";
    }
}
