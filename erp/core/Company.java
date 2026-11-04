package erp.core;

import erp.core.exception.ProductNotSuppliedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Company implements java.io.Serializable {
    @java.io.Serial
    private static final long               serialVersionUID = 202610272000L;
    private int                             _id;
    private String                          _name;
    private Map<Product, SuppliedProduct>   _productSupplies;

    //Constructor----------------------------------------------------------------------------------
    public Company(int id, String name){
        _id = id;
        _name = name;
        _productSupplies = new TreeMap<>();
    }
    
    //Getters--------------------------------------------------------------------------------------

    public int id(){ return  _id; }
    public String name(){ return _name; }
    
    public SuppliedProduct supply(Product prd) throws ProductNotSuppliedException {
        SuppliedProduct sp = _productSupplies.get(prd);
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

    boolean changeInventory(Product prd, int delta, double factor){
        SuppliedProduct sp = _productSupplies.get(prd); 
        //Not in
        if(sp == null){
            if(delta >= 0 && factor > 0){
                sp = new SuppliedProduct(prd, this, factor, delta);
                _productSupplies.put(prd, sp);
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
    public boolean equals(Object o){
        if((o instanceof Company cmp)){
            return Objects.equals(cmp.id(), _id);
        }
        return false;
    }
    @Override
    public int hashCode(){
        return Objects.hash(_id);
    }


    @Override 
    public String toString(){
        return String.format("%d - %s", _id, _name);
    }
}
