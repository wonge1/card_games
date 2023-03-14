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
                cpu.hit(p1.getPoints());
                pointTracker21(false);
            }
        }
    }

    public void pointTracker21(boolean player) {//true tracks player, while false tracks cpu, win condtions are tracked during cpu tracking
        if(player) {
            p1.hasAce();//checks for aces in the hand and then checks score to evaluate worth of aces
            p1.display("Player");//shows hand and points of p1
            if(p1.getPoints() > 21) {//checks if p1 is bust
                System.out.println();
                System.out.println("GAME OVER YOU LOSE");
                gameCheck();
            }
        }
        else {
            cpu.hasAce();//checks for aces in the hand and then checks score to evaluate worth of aces
            cpu.display("Dealer");//shows dealer hand and points
            if(p1.getPoints() < cpu.getPoints() && cpu.getPoints() <= 21) {//win/lose condition
                System.out.println();
                System.out.println("GAME OVER YOU LOSE");
                gameCheck();

            }
            else if(cpu.getPoints() > 21) {
                System.out.println();
                System.out.println("YOU WIN");
                p1.addMoney(reward*2);
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
}
