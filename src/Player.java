import java.util.*;
public class Player {
    private final HashSet<Card> hand = new HashSet<>();
    private int money = 0;
    public Player(int money) {this.money = money;}
    public Player(){};

    public int getMoney(){
        return money;
    }
    public void setMoney(int money){
        this.money = money;
    }

    public HashSet<Card> getHand(){return hand;}

    public void addCard(Card card){this.hand.add(card);}


}
