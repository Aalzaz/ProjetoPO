package erp.core;

import erp.core.exception.parserExceptions.StringException;
import erp.core.exception.parserExceptions.IntegerException;

public class ImportedProduct extends Product{
    private String _originCountry;
    private double _customTax;

    //Constructor----------------------------------------------------------------------------------
    public ImportedProduct(int id, String name, ProductCategory category, int basePrice, int baseDeliveryTime, String origin, double tax) 
                            throws StringException, IntegerException{
        super(id, name, category, basePrice, baseDeliveryTime);
        _originCountry  = verifiedName(origin, "Páis de origem");
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