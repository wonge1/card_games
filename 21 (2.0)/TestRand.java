public class TestRand 
{
    private int carWidth = 10;
    private int carHeight = 8;
    private boolean startOrEnd = true;

    //testing the printing of ascii cards
    public TestRand()
    {

    }

    public void printCard()
    {
        for(int i = 0; i < carHeight; i++)
        {
            for(int j = 0; j < carWidth; j++)
            {
                if((i == 0 || i == carHeight-1) && j > 0 && j < carWidth-1) //top and bottom of card, leaving corners blank
                    System.out.print("-");
                else if((j == 0 || j == carWidth-1) && i > 0 && i < carHeight-1) //printing sides
                    System.out.print("|");
                else if((j == 2 || j == carWidth-3) && (i == 1 || i == carHeight-2)) //printing card attributes
                    System.out.print("A");
                else
                    System.out.print(" ");
                
                
            }

            System.out.println();
        }
        
    }
}
