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
        System.out.print(z+" ");
        displayHand();
        System.out.print(z+" ");
        displayPoints();
    }

    public void displayHand()
    {
        System.out.print("Hand: ");

        for(int i = 0; i < hand.size(); i ++)
        {
            int k = hand.get(i);
            if(k == 1)
            {
                System.out.print("ACE, ");
            }
            else if(k == 11)
            {
                System.out.print("JACK, ");
            }
            else if(k == 12)
            {
                System.out.print("QUEEN, ");
            }
            else if(k == 13)
            {
                System.out.print("KING, ");
            }
            else 
            {
                System.out.print(k + ", ");
            }
        }
    }

    public void displayPoints()
    {
        System.out.print("Points: ");
        System.out.println(pointCount);
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


