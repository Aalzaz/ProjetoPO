package erp.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import erp.core.exception.parserExceptions.InvalidClient;


public class Client implements Serializable{
    private String                  _email;
    private String                  _name;
    private double                  _totalSpent;
    private Map<Integer, Basket>    _baskets;

//Constructor----------------------------------------------------------------------------------
    public Client(String email, String name) throws InvalidClient{
        verify(name, email);
        _name  = name;
        _email = email;
        _baskets = new HashMap<>();
    }
//Getters--------------------------------------------------------------------------------------

    public Basket basketFor(Company cmp) { return _baskets.get(cmp.id()); }
    public String name(){ return _name; }
    public String email(){ return _email;}
    public double totalSpent(){ return _totalSpent; }

//---------------------------------------------------------------------------------------------
    private void verify(String name, String email) throws InvalidClient{
        stringVerifier(name);
        stringVerifier(email);
    }

    private void stringVerifier(String name) throws InvalidClient{
        if(name == null || name.length() == 0)
            throw new InvalidClient("NOME", "Não dever ser vazio/nulo");
    }


    void debit(double val){
        _totalSpent += val;
    }
    boolean addBasket(Basket basket) {
        if(_baskets.containsKey(basket.company().id()))
            return false;
    _baskets.put(basket.company().id(), basket);
    return true;
}
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Client c && Objects.equals(_email, c._email);
    }

    @Override 
    public String toString(){
        return String.format(Locale.US, "%s - %s - %.2f", _email, _name, _totalSpent);
    }
    
}
