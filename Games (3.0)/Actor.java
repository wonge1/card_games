import java.util.*;
/**
 * Write a description of class Actor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Actor
{
    // instance variables - replace the example below with your own
    protected ArrayList<Card> hand = new ArrayList<Card>();
    protected boolean defeated = false;

    public void display(Object z) {//whoever we are displaying for
        System.out.println(z.toString().toUpperCase());
        displayHand();
    }

    public void displayHand() {
        //car dimensions
        int cardWidth = 10;
        int cardHeight = 8;

        int currCard = 1;

        int i = 0; //col determinate
        int j = 0; //row determinate

        //try and figure out a way to avoid manipulating for loop variable outside of loop
        while(i < cardHeight) {
            while(j < cardWidth)  {
                Card card = hand.get(currCard-1);
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

                if(j == cardWidth && currCard < hand.size()) {
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

    public void reset() {
        hand.clear();
    }

    public void newCard() {
        SoundPlayer sfx = new SoundPlayer("Sound/CardDraw.wav");
        Card card = Deck.deal();
        sfx.play();
        hand.add(card);
    }

    public void newCard(Card card) {//for testing purposes 
        hand.add(card);
    }

    public ArrayList<Card> getHand() {return hand;}

    
}


