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
    private static Scanner in;
    private static Player p1; //player object
    private static Dealer cpu; //dealer object
    private static int reward;
    private static Deck deck; //need this to initialize deck which is static
    private static enum GameType {
        TwentyOne, //twenty one
        Poker,
        GoFish
    }
    private static GameType currGameType;
    private static Game game;
    

    public Driver()
    {
        
    }

    public static void main(String[] arg)
    {
        in = new Scanner(System.in);
        deck = new Deck();
        p1 = new Player();
        cpu = new Dealer();
        getGameType();
        if(currGameType == GameType.TwentyOne)
        {
            game = new TwentyOne(p1, cpu, in);
        }
        game.run();
        in.close();
        
    }

    public static void getGameType() 
    {
        System.out.println("Select a game type.");
        for(GameType type : GameType.values())
        {
            System.out.println(type);
        }
        try {
            currGameType = GameType.valueOf(in.nextLine());
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
        
    }
}
