import java.util.HashSet;

public class AustinBot2 extends Player{
    private final Hand hand = new Hand();
    public AustinBot2(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        String decision = "fold";
        Hand handTotal = new Hand();
        int handRank = 0;
        handTotal.addCards(getHand().getCards());
        handTotal.addCards(cardsInPlay);
        handRank = handTotal.handRank();
        int strongHand = 6;
        int moderateHand = 3;
        double chanceToRaise = (handRank/10.0) + 0.1 ;
        if (currentBet.currentBet == 0){
            if (handRank > moderateHand){
                decision = "raise " + Math.min(getMoney(), (int) (handRank * 2));
            } else {
                decision = "check";
            }
        } else {
            if (handRank > strongHand){
                if (Math.random() < chanceToRaise){
                    decision = "raise " + Math.min(getMoney(), (int) ((handRank*50)/7));
                } else {
                    decision = "raise " + 1;
                }
            } else if (handRank > moderateHand) {
                if (Math.random() < chanceToRaise - 0.05){
                    decision = "raise " + Math.min(getMoney(), (int) ((handRank*50)/9));
                } else {
                    decision = "raise " + 1;
                }
            } else {
                if (Math.random() < chanceToRaise - 0.1){
                    decision = "raise " + 1;
                } else {
                    decision = "fold";
                }
            }
        }
        return decision;
    }
}
