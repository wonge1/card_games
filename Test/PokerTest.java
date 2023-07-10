

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

        assertEquals(44, toTest.straightCheck(cardHolder));//
        assertEquals(8, cardHolder.getHand().size());//ensure no changes occured in original actor class
    }

    @Test
    public void flushCheck() {
        Player cardHolder = new Player(null);
        cardHolder.newCard(new Card(2,3));
        cardHolder.newCard(new Card(4,3));
        cardHolder.newCard(new Card(5,3));
        cardHolder.newCard(new Card(5,3));

        assertEquals(-1, toTest.flushCheck(cardHolder));

        cardHolder.newCard(new Card(6,3));
        cardHolder.newCard(new Card(7,3));
        
        assertEquals(56, toTest.flushCheck(cardHolder));
        assertEquals(6, cardHolder.getHand().size());//ensure no changes occured in original actor class
    }

    @Test
    public void duplicateCheck() {
        int toCheck;

        Player dupHand = new Player(null);
        toCheck = toTest.duplicateCheck(dupHand);
        assertEquals(-1, toCheck);//base check

        dupHand.newCard(new Card(11,3));
        toCheck = toTest.duplicateCheck(dupHand);
        assertEquals(-1, toCheck);//base check

        dupHand.newCard(new Card(11,3));
        toCheck = toTest.duplicateCheck(dupHand);
        assertEquals(11, toCheck);//checking for pairs of jacks

        dupHand.newCard(new Card(11,3));
        toCheck = toTest.duplicateCheck(dupHand);
        assertEquals(37, toCheck);//checking for triple jacks

        dupHand.newCard(new Card(11,3));
        toCheck = toTest.duplicateCheck(dupHand);
        assertEquals(86, toCheck);//checking for quad jacks
    }

    @Test
    public void fullHouseCheck() {
        int toCheck;

        Player fullHouse = new Player(null);
        fullHouse.newCard(new Card(11,3));
        fullHouse.newCard(new Card(11,3));
        fullHouse.newCard(new Card(7,3));
        fullHouse.newCard(new Card(7,3));
        fullHouse.newCard(new Card(7,3));
        toCheck = toTest.duplicateCheck(fullHouse);
        assertEquals(69, toCheck);//base full house set up
        fullHouse.newCard(new Card(11,3));
        toCheck = toTest.duplicateCheck(fullHouse);//full house made from two triplets
        assertEquals(73, toCheck);
    }

}