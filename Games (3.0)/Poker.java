
import java.util.*;

public class Poker extends Game {
    private static final int ONE_PAIR_POINT_CONSTANT = 0;
    private static final int TWO_PAIR_POINT_CONSTANT = 13;
    private static final int TRIPLET_POINT_CONSTANT = 26;
    private static final int STRAIGHT_POINT_CONSTANT = 36;
    private static final int FLUSH_POINT_CONSTANT = 49;
    private static final int FULL_HOUSE_POINT_CONSTANT = 62;
    private static final int QUAD_POINT_CONSTANT = 75;
    private static final int STRAIGHT_FLUSH_POINT_CONSTANT = 49;//adding from straigh value

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
            checkHand(p1.getHand());
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

    public int checkHand(ArrayList<Card> currHand) {
        int toReturn = 0;//starts at 0 instead of -1 since this is total points, not if it found a hand
        if(currHand.contains(new Card(1))) {//if there is a high card, dont care about other cards as thats public
            toReturn = 1;
        }

        int straightValue = straightCheck(currHand, true);
        int flushValue = flushCheck(currHand);
        int dupValue = duplicateCheck(currHand);

        int points = Math.max(flushValue, dupValue);
        points = Math.max(points, straightValue);
        points = Math.max(toReturn, points);
        toReturn = points;

        return toReturn;
    }

    public int flushCheck(ArrayList<Card> currHand) {
        int toReturn = -1;
        int count = 0;

        ArrayList<Card> toCheck = new ArrayList<Card>();
        toCheck.addAll(currHand);
        toCheck.addAll(communityCards);

        while (toReturn == -1 && count < 4) {//check for all 4 suites
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
        ArrayList<Card> toCheck = new ArrayList<Card>();

        for (Card card : hand) {
            if (card.getSuite() == suite) {
                count++;
                if(card.getValue() > toReturn) {
                    toReturn = card.getValue();
                } else if(card.getValue() == 1) { //found an ace
                    toReturn = 14;
                }
                toCheck.add(card);//create a list of cards in possible flush
            }
        }
        
        if(count >= 5) {//if flush
            int straightValue = straightCheck(toCheck, false);
            if(straightValue != -1) {//straight flush
                return straightValue;
            } else {//just a flush
                return toReturn;
            }   
            
        } else {
            return -1;//no flush
        }
    }

    public int straightCheck(ArrayList<Card> currHand, boolean community) {//returns highest value of the straight for points
        ArrayList<Card> toCheck = new ArrayList<Card>();
        toCheck.addAll(currHand);
        if(community) {
            toCheck.addAll(communityCards);
        }

        //if(toCheck.size() > 0) {
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
        //}
        
        return -1;//if no straight found
    }

    public int duplicateCheck(ArrayList<Card> currHand) {//should be dup check
        
        ArrayList<DuplicateInfo> dupCollection = new ArrayList<DuplicateInfo>();
        ArrayList<Card> toCheck = new ArrayList<Card>();
        toCheck.addAll(currHand);
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
                        if(toReturn > -1) { //if there was a pair before this
                            twoPairs = true ;
                        }
                        toCheck = dupList.remove(index);
                        if(toCheck.getValue() > toReturn) {
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
