package erp.app.product;

import erp.app.company.SuppliedProduct;
import erp.app.company.Company;

import java.util.Map;
import java.util.Objects;
import java.util.HashMap;

public abstract class Product {
    private String                          _name;
    private int                             _id;
    private ProductCategory                 _category;
    private int                             _basePrice;
    private int                             _baseDeliveryTime;
    private Map<Integer, SuppliedProduct>   _suplyInCompany;

    //Constructor----------------------------------------------------------------------------------
    
    public Product(int id, String name, ProductCategory category, int basePrice, int baseDeliveryTime) {
        _id                 = id;
        _name               = name;
        _category           = category;
        _basePrice          = basePrice;
        _baseDeliveryTime   = baseDeliveryTime;
        _suplyInCompany     = new HashMap<>();
    }
    //Getters--------------------------------------------------------------------------------------

    public int id() {
        return _id;
    }
    public int basePrice(){
        return _basePrice;
    }
    public int baseDeliveryPrice(){
        return _baseDeliveryTime;
    }
    public ProductCategory category(){
        return _category;
    }
    public String name(){
        return _name;
    }
    public int totalUnits(){
        int total = 0;
        for(SuppliedProduct spd: _suplyInCompany.values()){
            total += spd.units();
        }
        return total;
    }
    public SuppliedProduct supliesCompany(Company cmp){
        return _suplyInCompany.get(cmp.id());
    }

    //----------------------------------------------------------------------------------------------
    public void supplyNewCompany(SuppliedProduct sdp){
        _suplyInCompany.put(sdp.company().id(), sdp);
    }

    abstract double recommendPrice();
    abstract String type();

    @Override 
    public boolean equals(Object o){
        if((o instanceof Product prd)){
            return Objects.equals(prd.id(), _id);
        }
        return false;
    }
    @Override
    public int hashCode(){
        return Objects.hash(_id);
    }

    @Override
    public String toString(){
        return String.format("%d - %s - %s - %s - %.2f - %.2f - %d - %d",
            _id, type(), _name, _category, recommendPrice(), 
            _basePrice, _baseDeliveryTime , totalUnits());
    }
}

