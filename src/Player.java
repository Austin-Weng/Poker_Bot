import java.util.*;
public class Player {
    private final Hand hand = new Hand();
    private int money = 0;
    public Player(int money) {this.money = money;}
    public Player(){};

    public int getMoney(){
        return money;
    }
    public void setMoney(int money){
        this.money = money;
    }

    public Hand getHand(){return hand;}

    public void addCard(Card card){this.hand.addCard(card);}
    public String decision(HashSet<Card> cardsInPlay, int pot, int amountToPay) {
        System.out.println("Current pot: " + pot);
        System.out.println("Your hand: " + getHand());
        System.out.println("Cards in play: " + cardsInPlay);
        System.out.println("Choose an action (fold/check/raise): ");

        Scanner scanner = new Scanner(System.in);
        String decision = scanner.nextLine().toLowerCase();

        while (!decision.equals("fold") && !decision.equals("check") && !decision.equals("raise")) {
            System.out.println("Invalid decision. Please choose again.");
            decision = scanner.nextLine().toLowerCase();
        }

        return decision;
    }
}
