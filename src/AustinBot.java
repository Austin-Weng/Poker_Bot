import java.util.HashSet;
import java.util.LinkedList;

public class AustinBot extends Player{
    private Hand hand = new Hand();
    public AustinBot(int money) {
        this.setMoney(money);
    }

    public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet){
        int handRank = 0;
        Hand handTotal = new Hand();
        System.out.println(hand);
        handTotal.addCards(hand.getCards());
        handTotal.addCards(cardsInPlay);
        handRank = handTotal.handRank();
        Deck tempDeck = new Deck();

        //Hand hand = cardsInPlay;
        if (currentBet.currentBet == 0){
            return "check";
        } else {
            return "call";
        }

    }

}
