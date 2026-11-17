package erp.core;

import erp.core.exception.InvalidProductException;

public class NationalProduct extends Product{
	@java.io.Serial
	private static final long serialVersionUID = 202616112100L;
	private static final int HIGH_QUANT = 10000, LOW_QUANT = 100;
	private static final int HIGH_PCT = 90, LOW_PCT = 130;

	//Constructor----------------------------------------------------------------------------------
	public NationalProduct(int id,String name, String category, int basePrice, int baseDeliveryTime) 
							throws InvalidProductException{
		super(id ,name, category, basePrice, baseDeliveryTime);
	}
	//----------------------------------------------------------------------------------------------
	@Override 
	double recommendPrice() {
	int units = totalUnits();
		if (units > HIGH_QUANT) 
			return basePrice() * HIGH_PCT / 100.0;
		if (units < LOW_QUANT)  
			return basePrice() * LOW_PCT / 100.0;
	return basePrice();
	}
	@Override
	public String extraString(){
		return "";
	}
	@Override
	String extraTypeInfo() {
		return "NACIONAL";
	}
}
	
