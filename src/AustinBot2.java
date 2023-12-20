import java.util.HashSet;
import java.util.Random;

public class AustinBot2 extends Player{
    private final Hand hand = new Hand();
    public AustinBot2(int money) {
        this.setMoney(money);
    }

//    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
//        // go all in
//        double chanceAllin = 0.15;
//        if (currentBet.currentBet == 0){
//            return "check";
//        } else {
//            if (Math.random() < chanceAllin){
//                if (getMoney() != 1 && getMoney() > currentBet.currentBet){
//                    return "raise " + (getMoney() - 1);
//                } else {
//                    return "raise " + 1;
//                }
//            } else {
//                if (currentBet.currentBet < getMoney()){
//                    return "call";
//                } else {
//                    return "fold";
//                }
//            }
//        }
//    }
    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        String decision = "fold";
        Hand handTotal = new Hand();
        int handRank = 0;
        handTotal.addCards(getHand().getCards());
        handTotal.addCards(cardsInPlay);
        handRank = handTotal.handRank();

        int strongHand = 6;
        int moderateHand = 2;
        double chanceToRaise = (handRank/10.0) + 0.5 ;
        if (currentBet.currentBet == 0){
            if (handRank > moderateHand){
                decision = "raise " + Math.min(getMoney(), (int) (handRank * 2));
            } else {
                decision = "check";
            }
        } else {
            if (handRank > strongHand){
                if (Math.random() < chanceToRaise){
                    decision = "raise " + Math.min(getMoney(),  (handRank*15));
                } else {
                    decision = "raise " + 1;
                }
            } else if (handRank > moderateHand) {
                if (Math.random() < chanceToRaise - 0.05){
                    decision = "raise " + Math.min(getMoney(), (handRank*10));
                } else {
                    if (currentBet.currentBet > getMoney()){
                        return "fold";
                    } else {
                        return "call";
                    }
                }
            } else {
                if (Math.random() < chanceToRaise - 0.1){
                    if (currentBet.currentBet > getMoney()){
                        return "fold";
                    } else {
                        return "call";
                    }
                } else {
                    decision = "fold";
                }
            }
        }
        return decision;
    }
}
