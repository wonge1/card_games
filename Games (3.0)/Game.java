
import java.util.*;

public abstract class Game {
    protected Player p1; //player object
    protected Dealer cpu; //dealer object
    protected boolean roundOver = false; //judges each round
    protected boolean gameOver = false; //tells if overall game is complete
    protected int reward;
    protected Scanner in;

    public Game(Player player, Dealer comp, Scanner scan)  {
        p1 = player;
        cpu = comp;
        in = scan;
    }
    
    public abstract void game(); //content to be looped

    public abstract void turn(boolean player); //what each person will do

    public void run() {
        while(gameOver == false) {
            while(roundOver == false) {
                game();
            }
            newRound();
        }
    }

    public void gameCheck() {//works for both
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println();
                p1.printMoney();
                System.out.println("Do you want to play another round? (Y/N)");
                String response = in.nextLine();
                if(response.equals("Y") || response.equals("y")) {
                    roundOver = true;
                    validInput = true;
                }
                else if(response.equals("N") || response.equals("n")) {
                    roundOver = true;
                    gameOver = true;
                    validInput = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }
        
        
    }

    public abstract void newRound();

    public abstract void newGame();
}
