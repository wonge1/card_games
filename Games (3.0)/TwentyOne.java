import java.awt.*;
import java.util.*;

public class TwentyOne extends Game {

    public TwentyOne(Player player, Dealer comp, Scanner scan) {
        super(player, comp, scan);
    }

    public void turn(boolean player) {
        if(player) {
            reward = p1.betAmount();//check for bet
            System.out.println("Total Pool is " + reward);
            while(!roundOver && p1.hit()) {//player draw loop, breaks when told to or points > 21
                pointTracker21(true);
            }
        }
        
        if(!player) {
            while(roundOver == false) {//cpu draw loop, breaks when told to or points > 21
                if (!cpu.hit())
                    pointTracker21(false);
            }
        }
    }

    public void pointTracker21(boolean player) {//true tracks player, while false tracks cpu, win condtions are tracked during cpu tracking
        if(player) {
            p1.display("Player");//shows hand and points of p1
            if(checkPoints(p1) > 21) {//checks if p1 is bust
                System.out.println();
                System.out.println("GAME OVER YOU LOSE");
                gameCheck();
            }
        }
        else {
            cpu.display("Dealer");//shows dealer hand and points
            if(checkPoints(cpu) > 21 || checkPoints(p1) >= checkPoints(cpu)) {
                System.out.println();
                System.out.println("YOU WIN");
                p1.addMoney(reward*2);
                gameCheck();
            } else {
                System.out.println();
                System.out.println("GAME OVER YOU LOSE");
                gameCheck();
            }
        }
    }

    public void game() {//game running that will be looped
        cpu.display("Dealer");
        p1.display("Player");
        turn(true); //players turn
        turn(false); //cpu turn
    }
    
    public int checkPoints(Actor a) {
        ArrayList<Integer> hand = a.getHand();
        int points = 0;
        int acesAsEleven = 0;
        for (Integer card : hand) {
            if(card < 11) { //if not face card
                if(card == 1) {
                    points = points + 11;
                    acesAsEleven++;
                }
                else 
                    points = points + card;
            }
            else //facecard
                points = points + 10;
        }

        while(points > 21 && acesAsEleven > 0) {//game adjusts points based on aces and score
            acesAsEleven--;
            points = points - 10;
        }
        
        return points;
    }
}
