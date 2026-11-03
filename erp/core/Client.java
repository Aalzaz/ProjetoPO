package erp.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Client implements Serializable{
    private String                  _email;
    private String                  _name;
    private double                  _totalSpent;
    private Map<Integer, Basket>    _baskets;

//Constructor----------------------------------------------------------------------------------
    public Client(String email, String name){
        _name  = name;
        _email = email;
        _baskets = new HashMap<>();
    }
//Getters--------------------------------------------------------------------------------------
    public Basket basketFor(Company cmp) {
        return _baskets.get(cmp.id());          
    }

    public String name(){
        return _name;
    }
    public String email(){
        return _email;
    }
    public double totalSpent(){
        return _totalSpent;
    }

//---------------------------------------------------------------------------------------------
    public void debit(double val){
        _totalSpent += val;
    }
    public void addBasket(Basket basket) {
    _baskets.put(basket.company().id(), basket);
}

    @Override 
    public String toString(){
        return String.format(Locale.US, "%s - %s - %.2f", _email, _name, _totalSpent);
    }
    
}
