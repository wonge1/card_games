package Basic;
public class Card {
    private int value;
    private int suite; //unicode value 3-6, heart, diamon, clubs, spades.

    public Card(int cardValue, int cardSuite) {
        value = cardValue;
        suite = cardSuite;
    }

    public int getValue() {
        return value;
    }

    public int getSuite() {
        return suite;
    }
    
}
