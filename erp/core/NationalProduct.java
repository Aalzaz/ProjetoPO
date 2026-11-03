package erp.core;

public class NationalProduct extends Product{
    private static final int HIGH_QUANT = 10000, LOW_QUANT = 100;
    private static final int HIGH_PCT = 90, LOW_PCT = 130;

    //Constructor----------------------------------------------------------------------------------
    public NationalProduct(int id,String name, ProductCategory category,
            int basePrice, int baseDeliveryTime) {
        super(id ,name, category, basePrice, baseDeliveryTime);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public String type(){
        return "NACIONAL";
    }
    @Override 
    public double recommendPrice() {
    int units = totalUnits();
        if (units > HIGH_QUANT) 
            return basePrice() * HIGH_PCT / 100.0;
        if (units < LOW_QUANT)  
            return basePrice() * LOW_PCT / 100.0;
    return basePrice();
    }
}
    
