
import java.util.*;

public class Poker extends Game {
    private ArrayList<Card> communityCards = new ArrayList<Card>();

    public Poker(Player player, Computer comp, Scanner scan) {
        super(player, comp, scan);
    }

    @Override
    public void game() {//game running that will be looped
        /* 
        System.out.println("Before Sort");
        for (Card card : communityCards) {
            System.out.println(card.getSuite());
        }
        
        
        
        String response = in.nextLine();
        */
        turn(true); //players turn
        turn(false); //cpu turn
    }

    @Override
    public void turn(boolean player) {
        if(player) {
            printComm();
            p1.display("Player");
            System.out.println(flushCheck(p1));
            p1.display("Player");
            System.out.println(straightCheck(p1));
            p1.display("Player");
            String response = in.nextLine();
        }

        if(!player) {

        }
    }

    @Override
    public void newRound() { //initialize start of game
        p1.newCard();
        p1.newCard();
        cpu.newCard();
        cpu.newCard();
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

    private boolean flushCheck(Actor currHand) {
        ArrayList<Card> toCheck = new ArrayList<Card>();
        toCheck.addAll(currHand.getHand());
        toCheck.addAll(communityCards);

        return cardSuiteCount(toCheck, 3) == 5 || 
            cardSuiteCount(toCheck, 4) == 5 || 
            cardSuiteCount(toCheck, 5) == 5 || 
            cardSuiteCount(toCheck, 6) == 5;
    }

    private int cardSuiteCount(ArrayList<Card> hand, int value) { //give total cards that match a given value
        int toReturn = 0;
        for (Card card : hand) {
            if (card.getSuite() == value) {
                toReturn ++;
            }
        }
        return toReturn;
    }

    private int straightCheck(Actor currHand) {//returns highest value of the straight for points
        ArrayList<Card> toCheck = new ArrayList<Card>();
        toCheck.addAll(currHand.getHand());
        toCheck.addAll(communityCards);
        
        toCheck.sort(new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.getValue() - c2.getValue();
            } 
        });
        return 1;
    }

    private int cardValueCount(Actor currHand, int value) { //give total cards that match a given value
        int toReturn = 0;
        ArrayList<Card> toCheck = currHand.getHand();
        toCheck.addAll(communityCards);
        for (Card card : toCheck) {   
            if (card.getValue() == value) {
                toReturn ++;
            }
        }
        return toReturn;
    }

    

    private void printComm() {
        //card dimensions
        int cardWidth = 10;
        int cardHeight = 8;

        int currCard = 1;

        int i = 0; //col determinate
        int j = 0; //row determinate

        //try and figure out a way to avoid manipulating for loop variable outside of loop
        while(i < cardHeight) {
            while(j < cardWidth)  {
                Card card = communityCards.get(currCard-1);
                if((i == 0 || i == cardHeight-1) && j > 0 && j < cardWidth-1) //top and bottom of card, leaving corners blank
                    System.out.print("-");
                else if((j == 0 || j == cardWidth-1) && i > 0 && i < cardHeight-1) //printing sides
                    System.out.print("|");
                else if((j == 2 && i == 1) || (j == cardWidth-3 && i == cardHeight-2)) {//printing card numbers
                    int k = card.getValue();
                    if(k == 1)
                        System.out.print("A");
                    else if(k == 11)
                        System.out.print("J");
                    else if(k == 12)
                        System.out.print("Q");
                    else if(k == 13)
                        System.out.print("K");
                    else if(k == 10)
                        System.out.print("T");
                    else 
                        System.out.print(k);
                } 
                else if((j == cardWidth-3 && i == 1) || (j == 2 && i == cardHeight-2)){ 
                    System.out.printf("%c", card.getSuite());
                }
                else
                    System.out.print(" "); //empty portions of card

                j++;

                if(j == cardWidth && currCard < communityCards.size()) {
                    j = 0;
                    currCard++;
                }
            }

            System.out.println();
            i++;
            j = 0;
            currCard = 1;
        }
    }
}
