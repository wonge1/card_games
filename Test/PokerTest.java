

//testing imports
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

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
        assertEquals(cardHolder.getHand().size(), 8);//ensure no changes occured in original actor class
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
        assertEquals(cardHolder.getHand().size(), 6);//ensure no changes occured in original actor class
    }

    @Test
    public void duplicateCheck() {
        Player cardHolder = new Player(null);
        cardHolder.newCard(new Card(7,3));
        cardHolder.newCard(new Card(2,3));
        cardHolder.newCard(new Card(4,3));
        cardHolder.newCard(new Card(5,3));
        cardHolder.newCard(new Card(5,3));
        cardHolder.newCard(new Card(6,3));
        cardHolder.newCard(new Card(7,3));
        cardHolder.newCard(new Card(7,3));
        cardHolder.newCard(new Card(7,3));
        ArrayList<DuplicateInfo> toCheck = toTest.createDuplicateList(cardHolder);
        
        assertTrue(toCheck.contains(new DuplicateInfo(4)));
        assertTrue(toCheck.contains(new DuplicateInfo(2)));
        assertEquals(cardHolder.getHand().size(), 9);//ensure no changes occured in original actor class
    }

}