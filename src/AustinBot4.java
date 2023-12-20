import java.util.HashSet;

public class AustinBot4 extends Player{
    private Hand hand = new Hand();
    public AustinBot4(int money) {
        this.setMoney(money);
    }
    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet) {
        double heuristic = 0;
        Hand handTotal = new Hand();
        int handRank = 0;
        //System.out.println(getHand());
        handTotal.addCards(getHand().getCards());
        handTotal.addCards(cardsInPlay);
        handRank = handTotal.handRank();
        String decision = "fold";

        boolean bet = false;
        int amountBet = 0;

        heuristic = handRank * 100 - currentBet.currentBet/2.0;
        if (heuristic < 80){
            decision =  "fold";
        }
        if (currentBet.currentBet == 0){
            if (heuristic > 250){
                decision = "raise " + Math.min(getMoney(), (int) (Math.random() * 10));
            } else {
                decision = "check";
            }
        } else {
            if (heuristic < 300 && heuristic > 80){
                if (currentBet.currentBet > getMoney() * 0.4) {
                    decision = "fold";
                } else if (getMoney() > currentBet.currentBet){
                    decision = "call";
                }
            } else if (heuristic < 600 && heuristic > 300){
                if (currentBet.currentBet > getMoney() * 0.6) {
                    decision = "fold";
                } else {
                    if (heuristic > 400){
                        decision = "raise " + (int) (Math.min(getMoney(), heuristic/15));
                    } else {
                        decision = "call";
                    }
                }
            }
        }
        return decision;
    }
}