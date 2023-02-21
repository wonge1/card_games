import java.awt.*;
import java.util.*;


/**
 * Write a description of class Driver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Driver
{
    // instance variables - replace the example below with your own
    private static Deck deck; //the record of cards left
    private static Player p1; //player object
    private static Dealer cpu; //dealer object
    private static boolean roundOver; //judges each round
    private static boolean gameOver; //tells if overall game is complete
    private static int reward;

    public static void test()
    {
        deck.display();

    }
    
    public Driver()
    {
        //setting all needed objects
        deck = new Deck();
        p1 = new Player();
        cpu = new Dealer();
        roundOver = false;
        gameOver = false;
        
    }

    public static void main(String[] arg)
    {
        deck = new Deck();
        p1 = new Player();
        cpu = new Dealer();
        roundOver = false;
        gameOver = false;
        while(gameOver == false)
        {
            while(roundOver == false)
            {
                game();

            }
            newRound();
        }
    }
    
    public static void game()
    {
        cpu.display("Dealer");
        p1.display("Player");
        turn(true); //players turn
        turn(false); //cpu turn
    }

    public static void turn(boolean player)
    {
        if(player)
        {
            reward = p1.betAmount();//check for bet
            System.out.println("Total Pool is " + reward);
            while(!roundOver && p1.hit()) //player draw loop, breaks when told to or points > 21
            {                
                pointTracker(true);
            }
        }
        
        if(!player)
        {
            while(roundOver == false) //cpu draw loop, breaks when told to or points > 21
            {
                cpu.hit(p1.getPoints());
                pointTracker(false);
            }
        }
    }

    public static void gameCheck()
    {
        Scanner in = new Scanner(System.in);
        System.out.println();
        p1.printMoney();
        System.out.println("Do you want to play another round? (Y/N)");
        String response = in.nextLine();
        if(response.equals("Y") || response.equals("y"))
        {
            roundOver = true;
        }
        else
        {
            roundOver = true;
            gameOver = true;
        }
    }

    public static void pointTracker(boolean player)//true tracks player, while false tracks cpu, win condtions are tracked during cpu tracking
    {
        Scanner in = new Scanner(System.in);
        if(player)
        {
            p1.hasAce();//checks for aces in the hand and then checks score to evaluate worth of aces
            p1.display("Player");//shows hand and points of p1
            if(p1.getPoints() > 21)//checks if p1 is bust
            {
                
                System.out.println();
                System.out.println("GAME OVER YOU LOSE");
                gameCheck();
            }
        }
        else
        {
            cpu.hasAce();//checks for aces in the hand and then checks score to evaluate worth of aces
            cpu.display("Dealer");//shows dealer hand and points
            if(p1.getPoints() < cpu.getPoints() && cpu.getPoints() <= 21) //win/lose condition
            {
               
                System.out.println();
                System.out.println("GAME OVER YOU LOSE");
                gameCheck();

            }
            else if(cpu.getPoints() > 21)
            {
                
                System.out.println();
                System.out.println("YOU WIN");
                p1.addMoney(reward*2);
                gameCheck();
            }
        }
    }

    public static void newRound()
    {
        p1.reset();
        cpu.reset();
        roundOver = false;

    }

    public static void newGame()
    {
        p1.reset();
        cpu.reset();
        roundOver = false;
        gameOver = false;
    }
}
