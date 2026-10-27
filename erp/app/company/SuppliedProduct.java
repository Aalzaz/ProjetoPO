package erp.app.company;
import erp.app.product.Product;
public class SuppliedProduct {
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
        return _product.recommendPrice() * _factor;
    }

    public void update(double factor, int units){
        _factor     = factor;
        _units      = units;
    }
    @Override 
    public String toString(){
        return String.format("%d %d %d %.1f %.2f", _company.id(), _product.id(), _units, _factor, price());
    }

}