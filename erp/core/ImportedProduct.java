package erp.core;

public class ImportedProduct extends Product{
    private String _originCountry;
    private double _customTax;

    //Constructor----------------------------------------------------------------------------------
    public ImportedProduct(int id, String name, ProductCategory category,
            int basePrice, int baseDeliveryTime, String origin, double tax) {
        super(id, name, category, basePrice, baseDeliveryTime);
        _originCountry  = origin;
        _customTax      = tax;
        
    }
    //----------------------------------------------------------------------------------------------
    @Override 
    public boolean hasOrigin(String orig){ return orig.equals(_originCountry); }
    
    @Override
    public String toString(){ return super.toString() + " - " + _originCountry + " - " + _customTax; }
    
    @Override
    public String type(){ return "IMPORTADO"; } 
    
    @Override 
    double recommendPrice(){ return basePrice() *(100 + _customTax)/100; }
    
    
}