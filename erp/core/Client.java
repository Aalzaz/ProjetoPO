package erp.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Client implements Serializable{
    private String                  _email;
    private String                  _name;
    private double                  _totalSpent;
    private Map<Company, Basket>    _baskets;

//Constructor----------------------------------------------------------------------------------
    public Client(String email, String name){
        _name  = name;
        _email = email;
        _baskets = new HashMap<>();
    }
//Getters--------------------------------------------------------------------------------------

    public Basket basketFor(Company cmp) { return _baskets.get(cmp); }
    public String name(){ return _name; }
    public String email(){ return _email;}
    public double totalSpent(){ return _totalSpent; }

//---------------------------------------------------------------------------------------------
    void debit(double val){
        _totalSpent += val;
    }
    boolean addBasket(Basket basket) {
        if(_baskets.containsKey(basket.company()))
            return false;
    _baskets.put(basket.company(), basket);
    return true;
}

    @Override 
    public String toString(){
        return String.format(Locale.US, "%s - %s - %.2f", _email, _name, _totalSpent);
    }
    
}
