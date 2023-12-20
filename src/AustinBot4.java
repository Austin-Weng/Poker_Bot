import java.util.HashSet;

public class AustinBot4 extends Player{
    private Hand hand = new Hand();
    public AustinBot4(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        // go all in
        double chanceAllin = 0.15;
        if (currentBet.currentBet == 0){
            return "check";
        } else {
            if (Math.random() < chanceAllin){
                if (getMoney() != 1 && getMoney() > currentBet.currentBet){
                    return "raise " + (getMoney() - 1);
                } else {
                    return "raise " + 1;
                }
            } else {
                if (currentBet.currentBet < getMoney()){
                    return "call";
                } else {
                    return "fold";
                }
            }
        }
    }
}