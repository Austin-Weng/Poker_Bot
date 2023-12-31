import java.util.*;
public class Hand {
    private HashSet<Card> handCards;
    private int handRank = 0;
    public Hand() {
        this.handCards = new HashSet<Card>();
        this.handRank = handRank();
    }

    public Hand(HashSet<Card> hand) {
        this.handCards = hand;
    }

    public void addCard(Card card) {
        handCards.add(card);
    }
    public void addCards(HashSet<Card> cards){
        handCards.addAll(cards);
    }

    public void clear() {
        handCards.clear();
    }

    public HashSet<Card> getCards() {
        return handCards;
    }
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Card card : handCards) {
            result.append(card).append(", ");
        }
        // Remove the trailing comma and space
        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }
        return result.toString();
    }
    public int size() {
        return handCards.size();
    }
    public void setHandRankZero(){
        handRank = 0;
    }
    public int handRank() {
        HashSet<Card> cards = handCards;

        if (isRoyalFlush(cards)) {
            return 10; // Royal Flush
        } else if (isStraightFlush(cards)) {
            return 9; // Straight Flush
        } else if (isFourOfAKind(cards)) {
            return 8; // Four of a Kind
        } else if (isFullHouse(cards)) {
            return 7; // Full House
        } else if (isFlush(cards)) {
            return 6; // Flush
        } else if (isStraight(cards)) {
            return 5; // Straight
        } else if (isThreeOfAKind(cards)) {
            return 4; // Three of a Kind
        } else if (isTwoPair(cards)) {
            return 3; // Two Pair
        } else if (isOnePair(cards)) {
            return 2; // One Pair
        } else {
            return 1; // High Card
        }
    }

    private boolean isRoyalFlush(HashSet<Card> cards) {

        return isStraightFlush(cards) && hasRank(cards, "10") && hasRank(cards, "Jack")
                && hasRank(cards, "Queen") && hasRank(cards, "King") && hasRank(cards, "Ace");
    }
    private boolean isStraightFlush(HashSet<Card> cards) {
        return isFlush(cards) && isStraight(cards);
    }
    private boolean isFourOfAKind(HashSet<Card> cards) {

        for (Card card : cards) {
            if (countRank(cards, card.getRank()) == 4) {
                return true;
            }
        }
        return false;
    }

    private boolean isFullHouse(HashSet<Card> cards) {
        boolean hasThree = false;
        boolean hasTwo = false;
        for (Card card : cards) {
            int count = countRank(cards, card.getRank());
            if (count == 3) {
                hasThree = true;
            } else if (count == 2) {
                hasTwo = true;
            }
        }
        return hasThree && hasTwo;
    }
    private boolean isFlush(HashSet<Card> cards) {
        Set<String> suits = new HashSet<>();
        for (Card card : cards) {
            suits.add(card.getSuit());
        }
        return suits.size() == 1;
    }
    private boolean isStraight(HashSet<Card> cards) {

        List<Card> sortedCards = new ArrayList<>(cards);
        sortedCards.sort(Comparator.comparingInt(this::cardValue));

        for (int i = 0; i < sortedCards.size() - 1; i++) {
            if (cardValue(sortedCards.get(i + 1)) - cardValue(sortedCards.get(i)) != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean isThreeOfAKind(HashSet<Card> cards) {
        for (Card card : cards) {
            if (countRank(cards, card.getRank()) == 3) {
                return true;
            }
        }
        return false;
    }
    private boolean isTwoPair(HashSet<Card> cards) {
        int pairCount = 0;
        for (Card card : cards) {
            if (countRank(cards, card.getRank()) == 2) {
                pairCount++;
            }
        }
        return pairCount == 4;
    }

    private boolean isOnePair(HashSet<Card> cards) {
        for (Card card : cards) {
            if (countRank(cards, card.getRank()) == 2) {
                return true;
            }
        }
        return false;
    }
    private int countRank(HashSet<Card> cards, String rank) {
        int count = 0;
        for (Card card : cards) {
            if (card.getRank().equals(rank)) {
                count++;
            }
        }
        return count;
    }

    private boolean hasRank(HashSet<Card> cards, String rank) {
        for (Card card : cards) {
            if (card.getRank().equals(rank)) {
                return true;
            }
        }
        return false;
    }
    private int cardValue(Card card) {
        if(card.getRank() == "Jack") {
            return 11;
        }
        else if(card.getRank() == "Queen") {
            return 12;
        }
        else if(card.getRank() == "King") {
            return 13;
        }
        else if(card.getRank() == "Ace") {
            return 14;
        }
        return Integer.parseInt(card.getRank());
    }
}
