import java.util.HashSet;

public class AustinBot {
    private HashSet<Card> hand;
    private int money = 0;
    public AustinBot(int money) {this.money = money;}

    public int getMoney(){
        return money;
    }
    public void setMoney(int money){
        this.money = money;
    }

    public HashSet<Card> getHand(){return hand;}

    public void addCard(Card card){this.hand.add(card);}
}
