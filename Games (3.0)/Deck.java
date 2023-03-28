import java.util.*;

/**
 * Write a description of class Deck here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Deck
{
    //private static ArrayList<Integer>deck = new ArrayList<Integer>();
    private static ArrayList<Card>deck = new ArrayList<Card>();

    public Deck()
    {
        reset();
        //Player p1 = new Player();
    }

    public void reset() //makes the deck from scratch
    {
        deck.clear();
        for(int j = 0; j < 4; j++)//making the deck
        {
            for(int i = 1; i <= 13; i++)
            {
                deck.add(new Card(i, j+3));
            }
        }
    }
    
    public static Card deal()//simulates removing a card from the initial deck
    {
        Card ret = deck.remove((int)(Math.random()*deck.size()));//drawing the card
        return ret;
    }
}
