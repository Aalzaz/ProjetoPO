package erp.core;

public class Purchase implements Comparable<Purchase> {
    //TODO
    
    private int _id;

    public int id(){
        return _id;
    }

    @Override 
    public int compareTo(Purchase pur){
        return _id - pur.id();
    }
}
