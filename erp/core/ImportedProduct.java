package erp.app.product;
public class ImportedProduct extends Product {
    private String _originCountry;
    private double _customTax;

    //Constructor----------------------------------------------------------------------------------
    public ImportedProduct(int id, String name, ProductCategory category,
            int basePrice, int baseDeliveryTime, String origin, double tax) {
        _originCountry  = origin;
        _customTax      = tax;
        super(id, name, category, basePrice, baseDeliveryTime);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public String toString(){
        return String.format("%s - %s - %.1f", super.toString(), 
                            _originCountry, _customTax);
    }
    @Override
    public String type(){
        return "IMPORTADO";
    } 
    @Override 
    public double recommendPrice(){
        return basePrice() *(100 + _customTax)/100;
    }
    
    
}