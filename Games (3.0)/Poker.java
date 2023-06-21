
import java.util.*;

public class Poker extends Game {
    private ArrayList<Card> communityCards = new ArrayList<Card>();

    public Poker(Player player, Computer comp, Scanner scan) {
        super(player, comp, scan);
    }

    @Override
    public void game() {//game running that will be looped
        
        turn(true); //players turn
        turn(false); //cpu turn
    }

    @Override
    public void turn(boolean player) {
        if(player) {
            printComm();
            p1.display("Player");
            checkHand(p1);
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
        
        /* 
        p1.newCard(new Card(6,5));
        p1.newCard(new Card(13,3));
        communityCards.add(new Card(7, 3));
        communityCards.add(new Card(8, 5));
        communityCards.add(new Card(4, 6));
        communityCards.add(new Card(13, 4));
        communityCards.add(new Card(1, 6));
        */
    }

    public void checkHand(Actor currHand) {
        /* 
        System.out.println("Flush: " + flushCheck(currHand));
        System.out.println("High Value of Straight: " + straightCheck(currHand));
        ArrayList<Card> toCheck = currHand.getHand();
        System.out.println("Total " + 
            toCheck.get(0).getValue() + 
            ": " + 
            cardValueCount(currHand, toCheck.get(0).getValue()));
        System.out.println("Total " + toCheck.get(1).getValue() + 
            ": " + 
            cardValueCount(currHand, toCheck.get(1).getValue()));
        ArrayList<DuplicateInfo> list = createDuplicateList(currHand);
        for (DuplicateInfo info : list) {
            System.out.println("Number: " + info.getValue() + ", has " + info.getTotal() + " copies");
        }
        */
        
        int straightValue = straightCheck(currHand);
        boolean flush = flushCheck(currHand);
        ArrayList<DuplicateInfo> dupList = createDuplicateList(currHand);

        if(straightValue == 14 && flush) {//royal flush
            System.out.println("ROYAL FLUSH");
        } //else 
        if(straightValue != -1 && flush) {//straight flush
            System.out.println("STRAIGHT FLUSH");
        } //else 
        if(dupList.contains(new DuplicateInfo(4))) {//4 of a kind
            System.out.println("4 OF A KIND");
        } //else 
        if(dupList.contains(new DuplicateInfo(3)) && dupList.contains(new DuplicateInfo(2))) {//full house
            System.out.println("FULL HOUSE");
        } //else 
        if(flush) {//flush
            System.out.println("FLUSH");
        } //else 
        if(straightValue != -1) {//straight
            System.out.println("STRAIGHT");
        } //else 
        if(dupList.contains(new DuplicateInfo(3))) { //3 of a kind
            System.out.println("THREE OF A KIND");
        } //else 
        //2 pairs 
        if(dupList.contains(new DuplicateInfo(2))){//1 pair
            System.out.println("TWO OF A KIND");
        } else {
            System.out.println("NO HAND");
        }
        
        //stall
        String response = in.nextLine();
    }

    private boolean flushCheck(Actor currHand) {
        ArrayList<Card> toCheck = new ArrayList<Card>();
        toCheck.addAll(currHand.getHand());
        toCheck.addAll(communityCards);

        return cardSuiteCount(toCheck, 3) >= 5 || 
            cardSuiteCount(toCheck, 4) >= 5 || 
            cardSuiteCount(toCheck, 5) >= 5 || 
            cardSuiteCount(toCheck, 6) >= 5;
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

        //if theres an ace
        if(toCheck.contains(new Card(1))) {
            toCheck.add(new Card(14, 4));//add an ace so A,K,Q,J,10 works
        }

        toCheck.sort(new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return c1.getValue() - c2.getValue();
            } 
        });

        int count = 1;
        boolean valid = true;
        int currIndex = toCheck.size()-1;
        int currValue = toCheck.get(currIndex).getValue();
        while(count <= 4 && valid && currIndex >=4) {
            if(toCheck.contains(new Card(currValue-count))) {
                count++;
            } else {
                currIndex-=1;//skip to next valid card
                currValue = toCheck.get(currIndex).getValue();
                count = 1;
            }
        }
        if(count == 5) {//straight found 
            return toCheck.get(currIndex).getValue();
        }

        return -1;//if no straight found
    }

    private int cardValueCount(Actor currHand, int value) { //give total cards that match a given value
        int toReturn = 0;
        ArrayList<Card> toCheck = new ArrayList<Card>();
        toCheck.addAll(currHand.getHand());
        toCheck.addAll(communityCards);

        for (Card card : toCheck) {   
            if (card.getValue() == value) {
                toReturn ++;
            }
        }

        return toReturn;
    }

    private ArrayList<DuplicateInfo> createDuplicateList(Actor currHand) {
        
        ArrayList<DuplicateInfo> toReturn = new ArrayList<DuplicateInfo>();
        ArrayList<Card> toCheck = new ArrayList<Card>();
        toCheck.addAll(currHand.getHand());
        toCheck.addAll(communityCards);

        while(toCheck.size() > 0) {
            int count = 0;
            int index = toCheck.size()-1;//start at end of the array list
            int value = toCheck.get(index).getValue();
            while(index >= 0) {
                if(toCheck.get(index).getValue()==value) {
                    count++;
                    toCheck.remove(index);
                }
                index--;
            }
            toReturn.add(new DuplicateInfo(value, count));
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
