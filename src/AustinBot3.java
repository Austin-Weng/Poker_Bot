import java.util.HashSet;

public class AustinBot3 extends Player{
    private Hand hand = new Hand();
    public AustinBot3(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        if (currentBet.currentBet == 0){
            return "raise " + Math.min(getMoney(), 1);
        }
        if (currentBet.currentBet < getMoney()){
            return "raise " + Math.min(getMoney(), 1);
        } else {
            return "fold";
        }
    }


}