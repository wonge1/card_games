import java.awt.*;
import java.util.*;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    // instance variables - replace the example below with your own
    public int money = 1000;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {       
        for(int i = 0; i < 2; i ++)
        {
            newCard();
        }

    }

    public int betAmount()
    {
        boolean valid = false;
        Scanner in = new Scanner(System.in);
        System.out.println("Current Money: " + money);
        System.out.println("How much money do you want to bet?");
        int i = in.nextInt();//returns the amount intended to bet
        while(!valid)
        {
            if(i <= money)
            {
                money = money - i;
                valid = true;

            }
            else
            {
                System.out.println("Invalid Amount \nCurrent Money: " + money);
                System.out.println("How much money do you want to bet?");
                i = in.nextInt();
                
            }

        }
        return i;

    }
    
    public void printMoney()
    {
        System.out.println("Current Money: " + money);
        
    }

    public void addMoney(int amountWon)
    {
        money = money + amountWon;

    }

    public boolean hit()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Do you want another card? (Y/N)");
        System.out.println();
        String response = in.nextLine();

        if(response.equals("y") || response.equals("Y"))
        {
            newCard();           
            return true;
        }
        else
        {
            return false;
        }

        
        
    }

}