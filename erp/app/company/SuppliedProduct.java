package erp.app.company;
import erp.app.product.Product;
public class SuppliedProduct {
    private Product _product;
    private Company _company;
    private double  _factor;
    private int     _units;

    public SuppliedProduct(Product prd, Company cmp, double factor, int units){
        _company = cmp;
        _factor = factor;
        _units = units;
        _product = prd;
        prd.supplyNewCompany(this);
    }

    public int units(){
        return _units;
    }
    public Company company(){
        return _company;
    }
    
}