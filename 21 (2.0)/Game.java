public abstract class Game {
    private Player p1; //player object
    private Dealer cpu; //dealer object
    private boolean roundOver; //judges each round
    private boolean gameOver; //tells if overall game is complete
    private int reward;
    
    public void game()
    {
        cpu.display("Dealer");
        p1.display("Player");
        turn(true); //players turn
        turn(false); //cpu turn
    }

    public void turn(boolean player){

    }

    public void run()
    {
        
    }

    /*while(gameOver == false)
        {
            while(roundOver == false)
            {
                game();
            }
            newRound();
        } */
}
