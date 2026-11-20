package erp.core;

import erp.core.exception.InvalidProductException;

public class ImportedProduct extends Product{
	@java.io.Serial
	private static final long serialVersionUID = 202616112100L;
	private String _originCountry;
	private double _customTax;

	//Constructor----------------------------------------------------------------------------------
	public ImportedProduct(int id, String name, String category, int basePrice, int baseDeliveryTime, String origin, double tax) 
							throws InvalidProductException{
		super(id, name, category, basePrice, baseDeliveryTime);
		nameVerifier(origin, "PAÍS DE ORIGEM");
		_originCountry  = origin;
		_customTax      = tax;
		
	}
	//Getters---------------------------------------------------------------------------------------

	public String origin(){ return  _originCountry; }

	//----------------------------------------------------------------------------------------------
	@Override 
	public boolean matchesOrigin(String origin){ return origin.equalsIgnoreCase(_originCountry); }

	@Override 
	double recommendPrice(){ return basePrice() *(100 + _customTax)/100; }
	
	@Override
	public String extraString(){
		return " - " + _originCountry + " - " + _customTax;
	}
	
	@Override
	String extraTypeInfo() {
		return "IMPORTADO";
	}
}