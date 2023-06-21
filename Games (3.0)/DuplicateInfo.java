public class DuplicateInfo {
    private int value;
    private int total;

    public DuplicateInfo(int cardValue, int duplicates) {
        value = cardValue;
        total = duplicates;
    }

    public DuplicateInfo(int duplicates) {//when making temps of quads, triples, pairs
        value = -1;
        total = duplicates;
    }

    public int getValue() {
        return value;
    } 

    public int getTotal() {
        return total;
    }

    @Override//when checking for triples, pairs, etc
    public boolean equals(Object c){
        if(c instanceof DuplicateInfo){
             DuplicateInfo p = (DuplicateInfo) c;
             return this.value==p.getValue();
        } else
             return false;
    }
}
