import java.util.HashSet;

public class AustinBot1 extends Player{
//    private Hand hand = new Hand();
//    private int hrMultiplier = (int) (Math.random() * 100);
//    private int betMultiplier = (int) (Math.random() * 10);
//    private int check1 = (int) (Math.random() * 1000);
//    private int check2 =  check1 + (int) (Math.random() * 1000);
//    private int check3 = check2 + (int) (Math.random() * 1000);
//    private int check4 = check3 - Math.min(check3, (int) (Math.random() * 1000));
//    private int check5 = check3 + (int) (Math.random() * 1000);
//    private int check6 = check5 + Math.min(check5, (int) (Math.random() * 1000));
//    private double handMultiplier1 = Math.random();
//    private double handMultiplier2 = Math.random();
//    private double handMultiplier3 = Math.random();
//    private double handMultiplier4 = Math.random();
//    private int hueristicBetAmount1 = (int) (Math.random() * 1000);
//    private int hueristicBetAmount2 = (int) (Math.random() * 1000);
//    private int hueristicBetAmount3 = (int) (Math.random() * 1000);
//    private int hueristicBetAmount4 = (int) (Math.random() * 1000);
//    private double heuristic = 0;
//    private Hand handTotal = new Hand();

    public void variables(){
//        System.out.println("hrMultiplier: " + hrMultiplier);
//        System.out.println("betMultiplier: " + betMultiplier);
//        System.out.println("check1: " + check1);
//        System.out.println("check2: " + check2);
//        System.out.println("check3: " + check3);
//        System.out.println("check4: " + check4);
//        System.out.println("check5: " + check6);
//        System.out.println("check6: " + check6);
//        System.out.println("handMultiplier1: " + handMultiplier1);
//        System.out.println("handMultiplier2: " + handMultiplier2);
//        System.out.println("handMultiplier3: " + handMultiplier3);
//        System.out.println("handMultiplier4: " + handMultiplier4);
//        System.out.println("hueristicBetAmount1: " + hueristicBetAmount1);
//        System.out.println("hueristicBetAmount2: " + hueristicBetAmount2);
//        System.out.println("hueristicBetAmount3: " + hueristicBetAmount3);
//        System.out.println("hueristicBetAmount4: " + hueristicBetAmount4);
//        System.out.println("hueristic: " + heuristic);
//        System.out.println("hand: " + handTotal);
//        System.out.println("handRank: " + handTotal.handRank());
    }
    public AustinBot1(int money) {
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

        heuristic = handRank * 100 - currentBet.currentBet;
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
