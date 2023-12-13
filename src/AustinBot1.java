import java.util.HashSet;

public class AustinBot1 extends Player{
    private Hand hand = new Hand();
    public AustinBot1(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet) {
        return "fold";
    }
}
