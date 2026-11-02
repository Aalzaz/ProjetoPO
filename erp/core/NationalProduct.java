package erp.app.product;
public class NationalProduct extends Product{
    private final int HIGH_QUANT = 10000;
    private final double HIGH_QUANT_MULT = 0.90;
    private final int LOW_QUANT  = 100;
    private final double LOW_QUANT_MULT = 1.30;

    //Constructor----------------------------------------------------------------------------------
    public NationalProduct(int id,String name, ProductCategory category,
            int basePrice, int baseDeliveryTime) {
        super(id ,name, category, basePrice, baseDeliveryTime);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public String type(){
        return "NATIONAL";
    }
    @Override 
    public double recommendPrice(){
        int units = totalUnits();

        if (units > HIGH_QUANT) {
            return (basePrice() * HIGH_QUANT_MULT);
        } else if (units < LOW_QUANT) {
            return  basePrice() * LOW_QUANT_MULT;
        }
        return basePrice();
        
    }
    
}
