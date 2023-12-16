import java.util.HashSet;

public class AustinBot2 extends Player{
    private Hand hand = new Hand();
    public AustinBot2(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        int handRank = 0;
        Hand handTotal = new Hand();
        System.out.println(hand);
        handTotal.addCards(hand.getCards());
        handTotal.addCards(cardsInPlay);
        handRank = handTotal.handRank();
        //Hand hand = cardsInPlay;
        if (currentBet.currentBet == 0){
            if (handRank > 3){
                return "raise " + 5;
            } else {
                return "check";
            }
        } else {
            if (handRank > 5 && currentBet.currentBet < (double) (getMoney() * 0.2)) {
                int betAmount = currentBet.currentBet + 20;
                betAmount = Math.min(betAmount, getMoney()-1);
                return "raise " + betAmount;
            } else if (handRank < 5 && handRank > 2 && currentBet.currentBet < (double) (getMoney() * 0.2)) {
                int betAmount = currentBet.currentBet + 10;
                betAmount = Math.min(betAmount, getMoney()-1);
                return "raise " + betAmount;
            } else if (handRank == 2 && currentBet.currentBet < (double) (getMoney() * 0.15)) {
                return "call";
            } else {
                return "fold";
            }
        }
    }

}
