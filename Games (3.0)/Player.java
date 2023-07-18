
import java.util.*;

/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private Scanner in;
    // instance variables - replace the example below with your own
    private int money = 10000;

    /**
     * Constructor for objects of class Player
     */
    public Player(Scanner scan) {       
        in = scan;
    }

    public int betAmount() {
        boolean validInput = false;
        int i = -1;
        while(!validInput) {
            System.out.println("How much money do you want to bet?");
            try {
                i = Integer.parseInt(in.nextLine());//returns the amount intended to bet
                if(i <= money) {
                    money = money - i;
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }
        return i;
    }
    
    public void printMoney() {System.out.println("Current Money: " + money);}

    public void addMoney(int amountWon) {money = money + amountWon;}

    public int getMoney() {
        return money;
    }

    

}