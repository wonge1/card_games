import java.util.*;
/**
 * Write a description of class Dealer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dealer extends Actor
{
    // instance variables - replace the example below with your own
    
    
    /**
     * Constructor for objects of class Dealer
     */
    public Dealer()
    {
        newCard();
        newCard();
        
    }
    
    public boolean hit()
    {
        if(this.getPoints() <= 16)
        {
            this.newCard();
            System.out.println();
            return true;
        }       
        return false;
    }
   
}
