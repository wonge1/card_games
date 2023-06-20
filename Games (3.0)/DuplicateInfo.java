public class DuplicateInfo {
    private int value;
    private int total;

    public DuplicateInfo(int cardValue, int duplicates) {
        value = cardValue;
        total = duplicates;
    }

    public int getValue() {
        return value;
    } 

    public int getTotal() {
        return total;
    }
}
