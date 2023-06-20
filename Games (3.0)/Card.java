public class Card {
    private int value;
    private int suite; //unicode value 3-6, heart, diamon, clubs, spades.

    public Card(int cardValue, int cardSuite) {
        value = cardValue;
        suite = cardSuite;
    }

    public Card(int cardValue) {//to create comparison cards
        value = cardValue;
        suite = -1;
    }

    public int getValue() {
        return value;
    }

    public int getSuite() {
        return suite;
    }

    @Override
    public boolean equals(Object c){
        if(c instanceof Card){
             Card p = (Card) c;
             return this.value==p.getValue();
        } else
             return false;
    }
    
}
