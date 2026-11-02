package erp.core;

import java.util.Map;
import java.util.Objects;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

public abstract class Product implements Serializable {
    @java.io.Serial
    private static final long serialVersionUID = 202610272000L;
    private String                          _name;
    private int                             _id;
    private ProductCategory                 _category;
    private int                             _basePrice;
    private int                             _baseDeliveryTime;
    private Map<Integer, SuppliedProduct>   _supliesInCompany;

    //Constructor----------------------------------------------------------------------------------
    
    public Product(int id, String name, ProductCategory category, int basePrice, int baseDeliveryTime) {
        _id                 = id;
        _name               = name;
        _category           = category;
        _basePrice          = basePrice;
        _baseDeliveryTime   = baseDeliveryTime;
        _supliesInCompany     = new HashMap<>();
    }
    //Getters--------------------------------------------------------------------------------------

    public int id() {
        return _id;
    }
    public int basePrice(){
        return _basePrice;
    }
    public int baseDeliveryTime(){
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
        for(SuppliedProduct spd: _supliesInCompany.values()){
            total += spd.units();
        }
        return total;
    }
    public SuppliedProduct supliesCompany(Company cmp){
        return _supliesInCompany.get(cmp.id());
    }

    //----------------------------------------------------------------------------------------------
    public void supplyNewCompany(SuppliedProduct sdp){
        _supliesInCompany.put(sdp.company().id(), sdp);
    }

    public abstract double recommendPrice();
    public abstract String type();

    public boolean hasOrigin(String orig){
        return false;
    }

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
        //Had float's with ",": 10,00
        return String.format(Locale.US, "%d - %s - %s - %s - %.2f - %.2f - %d - %d",
            _id, type(), _name, _category, (double) _basePrice,
            recommendPrice(), _baseDeliveryTime, totalUnits());
    }
}

