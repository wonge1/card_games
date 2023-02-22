import java.util.*;

public class TestRand 
{
    private int cardWidth = 10;
    private int cardHeight = 8;

    //testing the printing of ascii cards
    public TestRand()
    {

    }

    public void printHand(int totalCards, ArrayList<Integer> list)
    {
        int currCard = 1;

        int i = 0; //col determinate
        int j = 0; //row determinate

        //try and figure out a way to avoid manipulating for loop variable outside of loop
        while(i < cardHeight)
        {
            while(j < cardWidth) 
            {
                if((i == 0 || i == cardHeight-1) && j > 0 && j < cardWidth-1) //top and bottom of card, leaving corners blank
                    System.out.print("-");
                else if((j == 0 || j == cardWidth-1) && i > 0 && i < cardHeight-1) //printing sides
                    System.out.print("|");
                else if((j == 2 && i == 1) || (j == cardWidth-3 && i == cardHeight-2)) //printing card attributes || j == carWidth-3
                    System.out.print("A");
                else
                    System.out.print(" ");

                j++;

                if(j == cardWidth && currCard < totalCards)
                {
                    j = 0;
                    currCard++;
                }
            }

            System.out.println();
            i++;
            j = 0;
            currCard = 1;
        }
        
    }
}
