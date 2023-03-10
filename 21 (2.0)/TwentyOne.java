import java.awt.*;
import java.util.*;

public class TwentyOne {
    private Scanner in;
    private Player p1; //player object
    private Dealer cpu; //dealer object
    private boolean roundOver; //judges each round
    private boolean gameOver; //tells if overall game is complete
    private int reward;
    

    public TwentyOne()
    {
        
    }

    public void turn(boolean player)
    {
        if(player)
        {
            reward = p1.betAmount();//check for bet
            System.out.println("Total Pool is " + reward);
            while(!roundOver && p1.hit()) //player draw loop, breaks when told to or points > 21
            {                
                pointTracker21(true);
            }
        }
        
        if(!player)
        {
            while(roundOver == false) //cpu draw loop, breaks when told to or points > 21
            {
                cpu.hit(p1.getPoints());
                pointTracker21(false);
            }
        }
    }

    public void gameCheck() //works for both
    {
        System.out.println();
        p1.printMoney();
        System.out.println("Do you want to play another round? (Y/N)");
        String response = in.nextLine();
        if(response.equals("Y") || response.equals("y"))
        {
            roundOver = true;
        }
        else if(response.equals("N") || response.equals("n"))
        {
            roundOver = true;
            gameOver = true;
        }
        
    }

    public void pointTracker21(boolean player)//true tracks player, while false tracks cpu, win condtions are tracked during cpu tracking
    {
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

    public void newRound()
    {
        p1.reset();
        cpu.reset();
        roundOver = false;

    }

    public void newGame()
    {
        p1.reset();
        cpu.reset();
        roundOver = false;
        gameOver = false;
    }
}
