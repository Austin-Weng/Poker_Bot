import java.util.HashSet;
import java.util.Random;

public class AustinBot3 extends Player{
    //Same as bot 1
    private Hand hand = new Hand();
    public AustinBot3(int money) {
        this.setMoney(money);
    }
    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        Hand handTotal = new Hand();
        int handRank = 0;
        handTotal.addCards(getHand().getCards());
        handTotal.addCards(cardsInPlay);
        handRank = handTotal.handRank();

        double raiseProbability = 0.2;

        if (handRank > 6) {
            raiseProbability = 0.75;
        } else if (handRank > 4) {
            raiseProbability = 0.5;
        }

        if (Math.random() < raiseProbability) {
            return "raise " + Math.min((int) (Math.random() * 300 + 100), getMoney());
        } else if (currentBet.currentBet == 0) {
            if (Math.random() < 0.1){
                return "raise " + Math.min(getMoney(), (int) (Math.random() * 50 + 1));
            } else {
                return "check";
            }
        } else {
            if (Math.random() < 0.2){
                return "fold";
            } else {
                if (currentBet.currentBet > getMoney()){
                    return "fold";
                } else {
                    return "call";
                }
            }
        }
    }
}