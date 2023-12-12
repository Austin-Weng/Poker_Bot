import java.util.*;
public class Player {
    private final Hand hand = new Hand();
    private int money = 0;
    public Player(int money) {this.money = money;}
    public Player(){}

    public int getMoney(){
        return money;
    }
    public void setMoney(int money){
        this.money = money;
    }

    public Hand getHand(){return hand;}

    public void addCard(Card card){this.hand.addCard(card);}


        public String decision(HashSet<Card> cardsInPlay, int pot, CurrentBet currentBet) {
            System.out.println("Current pot: " + pot);
            System.out.println("Your hand: " + getHand());
            //System.out.println("Cards in play: " + cardsInPlay);

            String decision;

            while (true) {
                System.out.println("Choose an action (fold/check/raise): ");
                Scanner scanner = new Scanner(System.in);
                decision = scanner.nextLine().toLowerCase();

                if (decision.equals("raise")) {
                    System.out.println("Enter the amount to raise: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid number: ");
                        scanner.next();  // Consume invalid input
                    }
                    int raiseAmount = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline character
                    if (raiseAmount > getMoney()) {
                        System.out.println("You don't have enough money. Please choose again.");
                    } else {
                        return decision + " " + raiseAmount;
                    }
                } else if (decision.equals("fold") || decision.equals("check")) {
                    return decision;
                } else {
                    System.out.println("Invalid decision. Please choose again.");
                }
            }
        }




    }
