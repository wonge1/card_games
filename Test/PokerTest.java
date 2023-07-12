

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
    public void straightCheckTest() {
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
    public void flushCheckTest() {
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
    public void duplicateCheckTest() {
        Player dupHand = new Player(null);
        assertEquals(-1, toTest.duplicateCheck(dupHand));//base check

        dupHand.newCard(new Card(11,3));
        assertEquals(-1, toTest.duplicateCheck(dupHand));//base check

        dupHand.newCard(new Card(11,3));
        assertEquals(11, toTest.duplicateCheck(dupHand));//checking for pairs of jacks

        dupHand.newCard(new Card(11,3));
        assertEquals(37, toTest.duplicateCheck(dupHand));//checking for triple jacks

        dupHand.newCard(new Card(11,3));
        assertEquals(86, toTest.duplicateCheck(dupHand));//checking for quad jacks
    }

    @Test
    public void fullHouseCheckTest() {
        Player fullHouse = new Player(null);
        fullHouse.newCard(new Card(11,3));
        fullHouse.newCard(new Card(11,3));
        fullHouse.newCard(new Card(7,3));
        fullHouse.newCard(new Card(7,3));
        fullHouse.newCard(new Card(7,3));
        assertEquals(69, toTest.duplicateCheck(fullHouse));//base full house set up

        fullHouse.newCard(new Card(11,3));//full house made from two triplets
        assertEquals(73, toTest.duplicateCheck(fullHouse));
    }

    @Test
    public void handCheckTest() {//misc checks for straigh flush and high card
        Player hand = new Player(null);
        hand.newCard(new Card(11,3));
        hand.newCard(new Card(4,4));
        hand.newCard(new Card(3,5));
        hand.newCard(new Card(7,3));
        hand.newCard(new Card(9,3));
        assertEquals(0, toTest.checkHand(hand));

        hand.newCard(new Card(1,6));
        assertEquals(1, toTest.checkHand(hand));

        hand.newCard(new Card(8,3));
        hand.newCard(new Card(10,3));
        assertEquals(96, toTest.checkHand(hand));
    }

    @Test
    public void pointPriorityTest() {//checking that the right points are assigned
        Player hand = new Player(null);
        hand.newCard(new Card(1,3));
        hand.newCard(new Card(2,4));
        hand.newCard(new Card(3,3));
        hand.newCard(new Card(4,3));
        hand.newCard(new Card(5,3));
        assertEquals(41, toTest.checkHand(hand));//currently a straight

        hand.newCard(new Card(7,3));
        assertEquals(54, toTest.checkHand(hand));//flush should now take prio
    }

}