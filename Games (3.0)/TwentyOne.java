
import java.util.*;

import Basic.Actor;
import Basic.Card;

public class TwentyOne extends Game {

    public TwentyOne(Player player, Computer comp, Scanner scan) {
        super(player, comp, scan);
    }

    @Override
    public void turn(boolean player) {
        if(player) {
            reward = p1.betAmount();//check for bet
            System.out.println("Total Pool is " + reward);
            while(!roundOver && hit(p1)) {//player draw loop, breaks when told to or points > 21
                pointTracker21(true);
            }
        }
        
        if(!player) {
            if(roundOver == false) { //check if player already busted
                while (checkPoints(cpu) <= 16) //draw until past 16 points
                    cpu.newCard();         
                pointTracker21(false);//checks final game state
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

    @Override
    public void game() {//game running that will be looped
        cpu.display("Dealer");
        p1.display("Player");
        turn(true); //players turn
        turn(false); //cpu turn
    }
    
    public int checkPoints(Actor a) {
        ArrayList<Card> hand = a.getHand();
        int points = 0;
        int acesAsEleven = 0;
        for (Card card : hand) {
            int cardValue = card.getValue();
            if(cardValue < 11) { //if not face card
                if(cardValue == 1) {
                    points = points + 11;
                    acesAsEleven++;
                }
                else 
                    points = points + cardValue;
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

    public boolean hit(Actor player) {
        boolean validInput = false;
        String response = "";
        System.out.println("Do you want another card? (Y/N)");
        System.out.println();
        while (!validInput) {
            try {
                response = in.nextLine();
                if(response.equals("y") || response.equals("Y")) {
                    player.newCard();        
                    validInput = true;   
                    return true;
                } else if(response.equals("n") || response.equals("N")) {
                    validInput = true;   
                    return false;
                } 
                else {throw new Exception();}
            } catch (Exception e) {
                System.out.println("Invalid Input");
            }
        }
        System.out.println("THIS SHOULD NEVER HAPPEN");
        return validInput;
    }

    public void newRound() {
        p1.reset();
        p1.newCard();
        p1.newCard();

        cpu.reset();
        cpu.newCard();
        roundOver = false;
    }
}
