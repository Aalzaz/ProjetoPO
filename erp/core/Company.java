package erp.core;

import erp.core.exception.ProductNotSuppliedException;
import erp.core.exception.parserExceptions.InvalidCompany;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Company implements java.io.Serializable, Comparable<Company> {
    
    private int                             _id;
    private String                          _name;
    private Map<Integer, SuppliedProduct>   _productSupplies;

    //Constructor----------------------------------------------------------------------------------
    public Company(int id, String name) throws InvalidCompany{
        nameVerifier(name);
        _id = id;
        _name = name;
        _productSupplies = new TreeMap<>();
    }
    
    //Getters--------------------------------------------------------------------------------------

    public int id(){ return  _id; }
    public String name(){ return _name; }
    
    public SuppliedProduct supply(Product prd) throws ProductNotSuppliedException {
        SuppliedProduct sp = _productSupplies.get(prd.id());
        if (sp == null || sp.units() == 0)
            throw new ProductNotSuppliedException(_id, prd.id());
        return sp;
    }
    public Collection<SuppliedProduct> availableProducts() {
        List<SuppliedProduct> availableProducts = new ArrayList<>(); 
        for(SuppliedProduct sp: _productSupplies.values()){
            if(sp.isAvailable()){
                availableProducts.add(sp);
            }
        }
        return availableProducts;
    }

    //---------------------------------------------------------------------------------------------


    private void nameVerifier(String name) throws InvalidCompany{
        if(name == null || name.length() == 0)
            throw new InvalidCompany();
    }

    boolean changeInventory(Product prd, int delta, double factor){
        SuppliedProduct sp = _productSupplies.get(prd.id()); 
        //Not in
        if(sp == null){
            if(delta >= 0 && factor > 0){
                sp = new SuppliedProduct(prd, this, factor, delta);
                _productSupplies.put(prd.id(), sp);
                return true;
            }
            return false;
        }
        int new_units = sp.units() + delta;
        if(new_units >= 0 && factor > 0){
            sp.update(factor, new_units);
            return true;
        }
        return false;
    }
    @Override 
    public int compareTo(Company cmp){
        //The smallest
        return _id - cmp.id();
    }

    @Override 
    public boolean equals(Object o){
        if((o instanceof Company cmp)){
            return Objects.equals(cmp.id(), _id);
        }
        return false;
    }
    @Override 
    public String toString(){
        return String.format("%d - %s", _id, _name);
    }
}
