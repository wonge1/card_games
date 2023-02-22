import java.util.*;
/**
 * Write a description of class Actor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Actor
{
    // instance variables - replace the example below with your own
    public int pointCount = 0;
    public ArrayList<Integer>hand = new ArrayList<Integer>();
    public int aceAmount = 0;
    
    public void display(Object z) //whoever we are displaying for
    {
        System.out.println(z+" ");
        displayHand();
        displayPoints();
    }

    public void displayHand()
    {
        //car dimensions
        int cardWidth = 10;
        int cardHeight = 8;

        int currCard = 1;

        int i = 0; //col determinate
        int j = 0; //row determinate

        //try and figure out a way to avoid manipulating for loop variable outside of loop
        while(i < cardHeight)
        {
            while(j < cardWidth) 
            {
                if((i == 0 || i == cardHeight-1) && j > 0 && j < cardWidth-1) //top and bottom of card, leaving corners blank
                    System.out.print("-");
                else if((j == 0 || j == cardWidth-1) && i > 0 && i < cardHeight-1) //printing sides
                    System.out.print("|");
                else if((j == 2 && i == 1) || (j == cardWidth-3 && i == cardHeight-2)) //printing card attributes
                {
                    int k = hand.get(currCard-1);
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
                else
                    System.out.print(" "); //empty portions of card

                j++;

                if(j == cardWidth && currCard < hand.size())
                {
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

    /* 
    public void displayHand()
    {
        System.out.print("Hand: ");

        for(int i = 0; i < hand.size(); i ++)
        {
            
        }
    }
    */

    public void displayPoints()
    {
        System.out.print("Points: ");
        System.out.println(pointCount + "\n");
    }

    public int getPoints()
    {
        return pointCount;
    }

    public void reset()
    {
        pointCount = 0;
        aceAmount = 0;
        hand.clear();
        newCard();
        newCard();
    }

    public void newCard()
    {
        int card = Deck.deal();
        if(card < 11)
        {
            if(card == 1)
            {
                pointCount = pointCount + 11;
                aceAmount++;
            }
            else 
            {
                pointCount = pointCount + card;
            }
        }
        else
        {
            pointCount = pointCount + 10;
        }
        hand.add(card);
    }
    
    public void hasAce()
    {
        while(pointCount > 21 && aceAmount > 0) //game adjusts points based on aces and score
        {
            aceAmount--;
            pointCount = pointCount - 10;
        }
    }
}


