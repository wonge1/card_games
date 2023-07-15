

//testing imports
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class PokerTest {

    private Poker toTest = new Poker(new Player(null),new Computer(),null);

    public PokerTest() {

    }

    @Test
    public void straightCheckTest() {
        ArrayList<Card> hand = new ArrayList<Card>();

        hand.add(new Card(2));
        hand.add(new Card(4));
        hand.add(new Card(5));
        hand.add(new Card(5));
        hand.add(new Card(6));
        assertEquals(toTest.straightCheck(hand, true),-1);//

        hand.add(new Card(7));
        hand.add(new Card(8));
        hand.add(new Card(10));
        assertEquals(44, toTest.straightCheck(hand, true));//
        assertEquals(8, hand.size());//ensure no changes occured in original actor class
    }

    @Test
    public void flushCheckTest() {
        ArrayList<Card> hand = new ArrayList<Card>();

        hand.add(new Card(2,3));
        hand.add(new Card(4,3));
        hand.add(new Card(5,3));
        hand.add(new Card(5,3));
        assertEquals(-1, toTest.flushCheck(hand));

        hand.add(new Card(6,3));
        hand.add(new Card(7,3));
        assertEquals(56, toTest.flushCheck(hand));
        assertEquals(6, hand.size());//ensure no changes occured in original actor class
    }

    //done in flush cehck method
    @Test
    public void straightFlushCheckTest() { 
        ArrayList<Card> hand = new ArrayList<Card>();

        hand.add(new Card(4,3));
        hand.add(new Card(5,3));
        hand.add(new Card(6,3));
        hand.add(new Card(7,3));
        hand.add(new Card(8,3));//straight flush
        assertEquals(93, toTest.flushCheck(hand));

        //testing flushes with aces
        hand.clear();
        hand.add(new Card(13,3));
        hand.add(new Card(13,3));
        hand.add(new Card(12,3));
        hand.add(new Card(12,3));
        hand.add(new Card(1,3));
        assertEquals(63, toTest.flushCheck(hand));

        hand.add(new Card(1,3));
        hand.add(new Card(2,3));
        hand.add(new Card(3,3));
        hand.add(new Card(4,3));
        hand.add(new Card(5,3));
        assertEquals(90, toTest.flushCheck(hand));

        hand.clear();
        hand.add(new Card(1,3));
        hand.add(new Card(10,3));
        hand.add(new Card(11,3));
        hand.add(new Card(12,3));
        hand.add(new Card(13,3));
        assertEquals(99, toTest.flushCheck(hand));
    }

    @Test
    public void duplicateCheckTest() {
        ArrayList<Card> hand = new ArrayList<Card>();
        assertEquals(-1, toTest.duplicateCheck(hand));//base check

        hand.add(new Card(11,3));
        assertEquals(-1, toTest.duplicateCheck(hand));//base check

        hand.add(new Card(11,3));
        assertEquals(11, toTest.duplicateCheck(hand));//checking for pairs of jacks

        hand.add(new Card(11,3));
        assertEquals(37, toTest.duplicateCheck(hand));//checking for triple jacks

        hand.add(new Card(11,3));
        assertEquals(86, toTest.duplicateCheck(hand));//checking for quad jacks
    }

    @Test
    public void fullHouseCheckTest() {
        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(new Card(11,3));
        hand.add(new Card(11,3));
        hand.add(new Card(7,3));
        hand.add(new Card(7,3));
        hand.add(new Card(7,3));
        assertEquals(69, toTest.duplicateCheck(hand));//base full house set up

        hand.add(new Card(11,3));//full house made from two triplets
        assertEquals(73, toTest.duplicateCheck(hand));
    }

    @Test
    public void handCheckTest() {//misc checks for straigh flush and high card
        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(new Card(11,3));
        hand.add(new Card(4,4));
        hand.add(new Card(3,5));
        hand.add(new Card(7,3));
        hand.add(new Card(9,3));
        assertEquals(0, toTest.checkHand(hand));

        hand.add(new Card(1,6));
        assertEquals(1, toTest.checkHand(hand));

        hand.add(new Card(8,3));
        hand.add(new Card(10,3));
        assertEquals(96, toTest.checkHand(hand));
    }

    @Test
    public void pointPriorityTest() {//checking that the right points are assigned
        ArrayList<Card> hand = new ArrayList<Card>();
        hand.add(new Card(1,3));
        hand.add(new Card(2,4));
        hand.add(new Card(3,3));
        hand.add(new Card(4,3));
        hand.add(new Card(5,3));
        assertEquals(41, toTest.checkHand(hand));//currently a straight

        hand.add(new Card(7,3));
        assertEquals(54, toTest.checkHand(hand));//flush should now take prio
    }

}