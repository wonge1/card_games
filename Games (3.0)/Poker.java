
import java.util.*;

public class Poker extends Game {
    private static final int ONE_PAIR_POINT_CONSTANT = 0;
    private static final int TWO_PAIR_POINT_CONSTANT = 13;
    private static final int TRIPLET_POINT_CONSTANT = 26;
    private static final int STRAIGHT_POINT_CONSTANT = 36;
    private static final int FLUSH_POINT_CONSTANT = 49;
    private static final int FULL_HOUSE_POINT_CONSTANT = 62;
    private static final int QUAD_POINT_CONSTANT = 75;
    private static final int STRAIGHT_FLUSH_POINT_CONSTANT = 85;

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
        
        
        int straightValue = straightCheck(currHand);
        boolean flush = flushCheck(currHand);
        ArrayList<DuplicateInfo> dupList = createDuplicateList(currHand);

        if(straightValue == 14 && flush) {//royal flush
            System.out.println("ROYAL FLUSH");
        } else if(straightValue != -1 && flush) {//straight flush
            System.out.println("STRAIGHT FLUSH");
        } else if(dupList.contains(new DuplicateInfo(4))) {//4 of a kind
            System.out.println("4 OF A KIND");
        } else if(dupList.contains(new DuplicateInfo(3)) && dupList.contains(new DuplicateInfo(2))) {//full house
            System.out.println("FULL HOUSE");
        } else if(flush) {//flush
            System.out.println("FLUSH");
        } else if(straightValue != -1) {//straight
            System.out.println("STRAIGHT");
        } else if(dupList.contains(new DuplicateInfo(3))) { //3 of a kind
            System.out.println("THREE OF A KIND");
        } else if(dupList.contains(new DuplicateInfo(2))){//1 pair
            System.out.println("TWO OF A KIND");
        } else {
            System.out.println("NO HAND");
        }
        //need high card and 2 pairs

        */
        //stall
        String response = in.nextLine();
    }

    public int flushCheck(Actor currHand) {
        int toReturn = -1;
        int count = 0;

        ArrayList<Card> toCheck = new ArrayList<Card>();
        toCheck.addAll(currHand.getHand());
        toCheck.addAll(communityCards);

        while (toReturn == -1 && count < 4) {
            toReturn = flushHigh(toCheck, count + 3);
            count++;
        }

        if(toReturn != -1) {
            return toReturn + FLUSH_POINT_CONSTANT;
        }

        return -1;
    }

    private int flushHigh(ArrayList<Card> hand, int suite) { //find high card of possible flush
        int toReturn = 0;
        int count = 0;

        for (Card card : hand) {
            if (card.getSuite() == suite) {
                count++;
                if(card.getValue() > toReturn) {
                    toReturn = card.getValue();
                }
            }
        }

        if(count >= 5) {
            return toReturn;
        } else {
            return -1;//no flush
        }
    }

    public int straightCheck(Actor currHand) {//returns highest value of the straight for points
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
            return toCheck.get(currIndex).getValue() + STRAIGHT_POINT_CONSTANT;
        }

        return -1;//if no straight found
    }

    public int duplicateCheck(Actor currHand) {//should be dup check
        
        ArrayList<DuplicateInfo> dupCollection = new ArrayList<DuplicateInfo>();
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
            dupCollection.add(new DuplicateInfo(value, count));
        }

        return calcDuplicatePoints(dupCollection);
    }

    private int calcDuplicatePoints(ArrayList<DuplicateInfo> dupList) {
        DuplicateInfo toCheck;
        DuplicateInfo fourOfAKind = new DuplicateInfo(4);
        DuplicateInfo threeOfAKind = new DuplicateInfo(3);
        DuplicateInfo twoOfAKind = new DuplicateInfo(2);
        int toReturn = -1;
        int index = -1;
        boolean twoPairs = false;
        boolean triplet = false;
        boolean fullHouse = false;
        
        if(dupList.contains(fourOfAKind)) {//theres at most a single 4 of a kind so dont need to check for highest
            index = dupList.indexOf(fourOfAKind);
            toCheck = dupList.get(index);
            toReturn = toCheck.getValue() + QUAD_POINT_CONSTANT;//add the 4 of a kind constant
        } else if(dupList.contains(threeOfAKind) || dupList.contains(twoOfAKind)) {
            index = dupList.indexOf(threeOfAKind);
            while(index != -1) {
                if(triplet) {//more than 1 triplet, so add pair count for full house
                    fullHouse = true;
                }
                toCheck = dupList.remove(index);
                if(toCheck.getValue() > toReturn) {
                    toReturn = toCheck.getValue();
                    triplet = true;
                }
                index = dupList.indexOf(threeOfAKind);
            }
            
            if(fullHouse) {
                toReturn += FULL_HOUSE_POINT_CONSTANT;//full house from two triplets
            } else if(dupList.contains(twoOfAKind)) { 
                if(triplet) {//at this point would mean a triplet was found
                    toReturn += FULL_HOUSE_POINT_CONSTANT;//full house from pair and triplet
                } else {//not a full house, instead a pair or two pair
                    index = dupList.indexOf(twoOfAKind);
                    while(index != -1) {
                        toCheck = dupList.remove(index);
                        if(toCheck.getValue() > toReturn) {
                            if(toReturn > -1) { //if there was a pair before this
                                twoPairs = true ;
                            }
                            toReturn = toCheck.getValue();//add the 3 of a kind constant
                        }
                        index = dupList.indexOf(twoOfAKind);
                    }

                    if(twoPairs) {
                        toReturn += TWO_PAIR_POINT_CONSTANT;//add two pair value
                    } else {
                        toReturn += ONE_PAIR_POINT_CONSTANT;
                    }
                }
            } else {//only triplet
                toReturn += TRIPLET_POINT_CONSTANT;
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
