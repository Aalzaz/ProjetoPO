package erp.core;

import java.util.Map;
import java.util.Objects;

import erp.core.exception.parserExceptions.StringException;
import erp.core.exception.parserExceptions.IntegerException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

public abstract class Product implements Serializable, Comparable<Product>, Verifier{
	@java.io.Serial
	private static final long serialVersionUID = 202610272000L;
	
	private String                          _name;
	private int                             _id;
	private ProductCategory                 _category;
	private int                             _basePrice;
	private int                             _baseDeliveryTime;
	private Map<Company, SuppliedProduct>   _supliesInCompany;

	//Constructor----------------------------------------------------------------------------------
	
	public Product(int id, String name, ProductCategory category, int basePrice, int baseDeliveryTime) 
					throws StringException, IntegerException{
		_id                 = id;
		_name               = verifiedName(name, "Nome");
		_category           = category;
		_basePrice          = verifiedPositiveInt(basePrice, "Preço");
		_baseDeliveryTime   = verifiedZeroOrMoreInt(baseDeliveryTime, "Prazo de entrega base");
		_supliesInCompany   = new HashMap<>();
	}
	//Getters--------------------------------------------------------------------------------------
	
	public int id() { return _id; }
	public int basePrice(){ return _basePrice;}
	public int baseDeliveryTime(){ return _baseDeliveryTime; }
	public ProductCategory category(){ return _category; }
	public String name(){ return _name; }
	
	abstract double recommendPrice();
	public abstract String type();
	public abstract String extraString();
	
	public int totalUnits(){
		int total = 0;
		for(SuppliedProduct spd: _supliesInCompany.values()){
			total += spd.units();
		}
		return total;
	}
	public SuppliedProduct suplyInCompany(Company cmp){
		return _supliesInCompany.get(cmp);
	}

	//----------------------------------------------------------------------------------------------

	void supplyNewCompany(SuppliedProduct sdp){
		_supliesInCompany.put(sdp.company(), sdp);
	}

	public boolean hasOrigin(String orig){
		return false;
	}
	
	@Override 
	public int compareTo(Product prd){
		//The smallest 
		return _id - prd.id(); 
	}

	@Override 
	public boolean equals(Object o){
		if((o instanceof Product prd)){
			return Objects.equals(prd.id(), _id);
		}
		return false;
	}
	@Override
	public int hashCode(){
		return Objects.hash(_id);
	}

	@Override
	public String toString(){
		return String.format(Locale.US, "%d - %s - %s - %s - %.2f - %.2f - %d - %d",
				_id, type(), _name, _category, (double) _basePrice,
				recommendPrice(), _baseDeliveryTime, totalUnits())
			+ extraString();
	}

}

