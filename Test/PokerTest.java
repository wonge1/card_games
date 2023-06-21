

import java.util.ArrayList;
import java.util.Scanner;

//testing imports
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PokerTest {

    private Poker toTest = new Poker(new Player(null),new Computer(),null);

    public PokerTest() {

    }

    @Test
    public void straightCheck() {
        Player cardHolder = new Player(null);
        cardHolder.newCard(new Card(2));
        cardHolder.newCard(new Card(4));
        cardHolder.newCard(new Card(5));
        cardHolder.newCard(new Card(5));
        cardHolder.newCard(new Card(6));

        assertEquals(toTest.straightCheck(cardHolder),-1);//

        cardHolder.newCard(new Card(7));
        cardHolder.newCard(new Card(8));
        cardHolder.newCard(new Card(10));

        assertEquals(toTest.straightCheck(cardHolder),8);//
    }

    @Test
    public void flushCheck() {
        Player cardHolder = new Player(null);
        cardHolder.newCard(new Card(2,3));
        cardHolder.newCard(new Card(4,3));
        cardHolder.newCard(new Card(5,3));
        cardHolder.newCard(new Card(5,3));

        assertFalse(toTest.flushCheck(cardHolder));

        cardHolder.newCard(new Card(6,3));
        cardHolder.newCard(new Card(7,3));
        
        assertTrue(toTest.flushCheck(cardHolder));
    }

}