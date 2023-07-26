
import java.util.*;


/**
 * Write a description of class Driver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Driver {
    private static Scanner in;
    private static Player p1; //player object
    private static Computer cpu; //comp object
    private static Deck deck; //need this to initialize deck which is static
    private static enum GameType {
        TwentyOne, //twenty one
        Poker,
        GoFish
    }
    private static GameType currGameType;
    private static Game game;
    

    public static void main(String[] arg) {
        SoundPlayer bgSound = new SoundPlayer("Sound/Music.wav");
        in = new Scanner(System.in);
        deck = new Deck();
        p1 = new Player(in);
        cpu = new Computer();
        getGameType();
        if(currGameType == GameType.TwentyOne) {
            game = new TwentyOne(p1, in);
        } else if(currGameType == GameType.Poker) {
            game = new Poker(p1, in);
        } else if(currGameType == GameType.GoFish) {
            
        }
        bgSound.play();
        game.run();
        in.close();
        bgSound.stop();
    }

    public static void getGameType() {
        boolean validInput = false;
        int counter = 0;
        System.out.println("Select a game type.");
        for(GameType type : GameType.values()) {
            System.out.println(counter + ": " + type);
            counter++;
        }
        while (!validInput) {
            try {
                int response = Integer.parseInt(in.nextLine());
                if (response > 2 || response < 0) 
                    throw new InputMismatchException();
                validInput = true;
                switch (response) {
                    case 0: currGameType = GameType.TwentyOne;
                        break;
                    case 1: currGameType = GameType.Poker;
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input please try again.");
            }
        }
        
        
    }
}
