package erp.app.company;

import java.util.HashMap;
import java.util.Map;

import erp.app.product.Product;

public class Company {
    private int                             _id;
    private String                          _name;
    private Map<Product, SuppliedProduct>   _productSupplies;

    //Constructor----------------------------------------------------------------------------------
    public Company(int id, String name){
        _id = id;
        _name = name;
        _productSupplies = new HashMap<>();
    }
    
    //Getters--------------------------------------------------------------------------------------

    public int id(){
        return  _id;
    }
    public String name(){
        return _name;
    }
    
    //---------------------------------------------------------------------------------------------

    public void changeInventroy(Product prd, int delta, double factor){
        SuppliedProduct sp = _productSupplies.get(prd); 
        //Not in
        if(sp == null){
            sp = SuppliedProduct(prd, this, factor, delta);

        }

    }

    @Override 
    public String toString(){
        return String.format("%d - %s", _id, _name);
    }
}
