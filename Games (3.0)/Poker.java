
import java.util.*;

public class Poker extends Game {
    private ArrayList<Card> communityCards = new ArrayList<Card>();

    public Poker(Player player, Computer comp, Scanner scan) {
        super(player, comp, scan);
    }

    @Override
    public void game() {//game running that will be looped
        System.out.println("Before Sort");
        for (Card card : communityCards) {
            System.out.println(card.getValue());
        }
        straightCheck(p1);
        System.out.println("After Sort");
        for (Card card : p1.getHand()) {
            System.out.println(card.getValue());
        }
        
        String response = in.nextLine();
        //cpu.display("Dealer");
        //p1.display("Player");
        //turn(true); //players turn
        //turn(false); //cpu turn
    }

    @Override
    public void turn(boolean player) {
        if(player) {

        }

        if(!player) {

        }
    }

    @Override
    public void newRound() { //initialize start of game
        communityCards.add(Deck.deal());
        communityCards.add(Deck.deal());
        communityCards.add(Deck.deal());
        communityCards.add(Deck.deal());
        communityCards.add(Deck.deal());
        communityCards.add(Deck.deal());
        communityCards.add(Deck.deal());
        communityCards.add(Deck.deal());
    }

    public void checkHand(Actor currHand) {
        //if (currHand.cardSuiteCount(reward)) { //royal flush
            
        //}
    }

    public boolean flushCheck(Actor currHand) {
        ArrayList<Card> toCheck = currHand.getHand();
        toCheck.addAll(communityCards);

        return cardSuiteCount(toCheck, 3) == 5 || 
            cardSuiteCount(toCheck, 4) == 5 || 
            cardSuiteCount(toCheck, 5) == 5 || 
            cardSuiteCount(toCheck, 6) == 5;
    }

    public void straightCheck(Actor currHand) {
        ArrayList<Card> toCheck = currHand.getHand();
        toCheck.addAll(communityCards);
        toCheck.sort(new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.getValue() - c2.getValue();
            } 
        });
    }

    
    public int cardValueCount(ArrayList<Card> hand, int value) { //give total cards that match a given value
        int toReturn = 0;
        for (Card card : hand) {   
            if (card.getValue() == value) {
                toReturn ++;
            }
        }
        return toReturn;
    }

    public int cardSuiteCount(ArrayList<Card> hand, int value) { //give total cards that match a given value
        int toReturn = 0;
        for (Card card : hand) {
            if (card.getSuite() == value) {
                toReturn ++;
            }
        }
        return toReturn;
    }
}
