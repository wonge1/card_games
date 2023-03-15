import java.util.*;

/**
 * Write a description of class Deck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Deck
{
    // instance variables - replace the example below with your own
    private static ArrayList<Integer>deck = new ArrayList<Integer>();

    public Deck()
    {
        reset();
        //System.out.println(deck.size());
        //Player p1 = new Player();
    }

    public void reset() //makes the deck from scratch
    {
        deck.clear();
        for(int j = 0; j < 4; j++)//making the deck
        {
            for(int i = 1; i <= 13; i++)
            {
                deck.add(i);
            }
        }
    }
    
    public void display()
    {
        /*
        for(int j = 0; j < deck.size(); j++)//making the deck
        {
            System.out.print(deck.get(j)+" ");
        }
        System.out.println();
        */
        System.out.print(deck.size());
    }

    public static int deal()//simulates removing a card from the initial deck
    {
        int ret = deck.remove((int)(Math.random()*deck.size()));//drawing the card
        return ret;
    }
}
