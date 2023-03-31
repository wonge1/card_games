
import java.util.*;

public class Poker extends Game {
    private ArrayList<Card> communityCards = new ArrayList<Card>();

    public Poker(Player player, Computer comp, Scanner scan) {
        super(player, comp, scan);
    }

    @Override
    public void game() {//game running that will be looped
        cpu.display("Dealer");
        p1.display("Player");
        turn(true); //players turn
        turn(false); //cpu turn
    }

    @Override
    public void turn(boolean player) {
        if(player) {

        }

        if(!player) {

        }
    }

    @Override
    public void newRound() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newRound'");
    }

    public void checkHand(Actor currHand) {
        //if (currHand.cardSuiteCount(reward)) { //royal flush
            
        //}
    }

    public boolean flushCheck(Actor currHand) {
        return currHand.cardSuiteCount(3) == 5 || 
            currHand.cardSuiteCount(4) == 5 || 
            currHand.cardSuiteCount(5) == 5 || 
            currHand.cardSuiteCount(6) == 5;
    }

    public void straightCheck(Actor a) {
        ArrayList<Card> toCheck = a.getHand();
        toCheck.addAll(communityCards);
    }
}
