
import java.util.*;

public abstract class Game {
    protected Player p1; //player object
    protected Dealer cpu; //dealer object
    protected boolean roundOver = false; //judges each round
    protected boolean gameOver = false; //tells if overall game is complete
    protected int reward;
    protected Scanner in;

    public Game(Player player, Dealer comp, Scanner scan) 
    {
        p1 = player;
        cpu = comp;
        in = scan;
    }
    
    public void game()
    {
        cpu.display("Dealer");
        p1.display("Player");
        turn(true); //players turn
        turn(false); //cpu turn
    }

    public abstract void turn(boolean player);

    public void run()
    {
        while(gameOver == false)
        {
            while(roundOver == false)
            {
                game();
            }
            newRound();
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
