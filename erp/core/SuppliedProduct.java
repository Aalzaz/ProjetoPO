package erp.core;

import java.io.Serializable;
import java.util.Locale;

public class SuppliedProduct implements Serializable{
    @java.io.Serial
    private static final long serialVersionUID = 202610272000L;
    private Product _product;
    private Company _company;
    private double  _factor;
    private int     _units;

//Constructor----------------------------------------------------------------------------------
    public SuppliedProduct(Product prd, Company cmp, double factor, int units){
        _company    = cmp;
        _factor     = factor;
        _units      = units;
        _product    = prd;
        prd.supplyNewCompany(this);
    }
//Getters--------------------------------------------------------------------------------------
    public int units(){
        return _units;
    }
    public Company company(){
        return _company;
    }
    public Product product(){
        return _product;
    }
    public double factor(){
        return _factor;
    }

//---------------------------------------------------------------------------------------------
    public double price(){
        return Math.round(_product.recommendPrice() * _factor);
    }

    public void update(double factor, int units){
        _factor     = factor;
        _units      = units;
    }
    public void decrease(int amount) {
        _units -= amount;
    }
    public boolean isAvailable() {
        return _units > 0;
    }
    @Override 
    public String toString(){
        return String.format(Locale.US, "%d - %d - %d - %s - %.2f",
                _company.id(), _product.id(), _units, _factor, price());
    }

}