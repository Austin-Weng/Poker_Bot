import java.util.HashSet;

public class AustinBot1 extends Player{
    private Hand hand = new Hand();
    public AustinBot1(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet) {
        int handRank = 0;
        Hand handTotal = new Hand();
        System.out.println(hand);
        handTotal.addCards(hand.getCards());
        handTotal.addCards(cardsInPlay);
        handRank = handTotal.handRank();

        boolean bet = false;
        int amountBet = 0;

        double hueristic = handRank * 100 - currentBet.currentBet * 1.3;
        if (currentBet.currentBet == 0){
            if (hueristic > 250){
                return "raise " + (int) (Math.min(getMoney(), hueristic/100));
            } else {
                return "check";
            }
        } else {
            if (hueristic < 0){
                return "fold";
            } else if (hueristic < 100){
                if (currentBet.currentBet > getMoney() * 0.2) {
                    return "fold";
                } else {
                    return "call";
                }
            } else if (hueristic < 300){
                if (currentBet.currentBet > getMoney() * 0.3) {
                    return "fold";
                } else {
                    if (hueristic > 200){
                        return "raise " + (int) (Math.min(getMoney(), hueristic/100));
                    }
                    return "call";
                }
            } else if (hueristic < 500){
                if (currentBet.currentBet > getMoney() * 0.4) {
                    return "fold";
                } else {
                    if (hueristic > 400){
                        return "raise " + (int) (Math.min(getMoney(), hueristic/100));
                    }
                    return "call";
                }
            } else {
                if (currentBet.currentBet > getMoney() * 0.6) {
                    return "fold";
                } else {
                    return "raise " + (int) (Math.min(getMoney(), hueristic/100));
                }
            }
        }
    }
}
