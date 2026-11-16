package erp.core;

import erp.core.exception.parserExceptions.InvalidProduct;

public class ImportedProduct extends Product{
    private String _originCountry;
    private double _customTax;

    //Constructor----------------------------------------------------------------------------------
    public ImportedProduct(int id, String name, ProductCategory category, int basePrice, int baseDeliveryTime, String origin, double tax) 
                            throws InvalidProduct{
        super(id, name, category, basePrice, baseDeliveryTime);
        nameVerifier(origin, "PAÍS DE ORIGEM");
        _originCountry  = origin;
        _customTax      = tax;
        
    }
    //----------------------------------------------------------------------------------------------
    @Override 
    public boolean hasOrigin(String orig){ return orig.equals(_originCountry); }
    
    @Override
    public String type(){ return "IMPORTADO"; } 
    
    @Override 
    double recommendPrice(){ return basePrice() *(100 + _customTax)/100; }
    
    @Override
    public String extraString(){
        return " - " + _originCountry + " - " + _customTax;
    }
}