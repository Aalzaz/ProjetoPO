package erp.core;

import java.util.Map;

import erp.core.exception.InvalidProductException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

public abstract class Product implements Serializable, Comparable<Product> {
	@java.io.Serial
	private static final long serialVersionUID = 202610272000L;
	
	private String                          _name;
	private int                             _id;
	private ProductCategory                 _category;
	private int                             _basePrice;
	private int                             _baseDeliveryTime;
	private Map<Integer, SuppliedProduct>   _supliesInCompany;

	//Constructor----------------------------------------------------------------------------------
	
	public Product(int id, String name, ProductCategory category, int basePrice, int baseDeliveryTime) 
					throws InvalidProductException{
		verify(name, basePrice, baseDeliveryTime);
		_id                 = id;
		_name               = name;
		_category           = category;
		_basePrice          = basePrice;
		_baseDeliveryTime   = baseDeliveryTime;
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
		return _supliesInCompany.get(cmp.id());
	}

	//----------------------------------------------------------------------------------------------

	protected void verify(String name, int basePrice, int baseDeliveryTime) throws InvalidProductException{
		nameVerifier(name, "NOME");
		positiveIntVerifier(basePrice, false, "PREÇO BASE");
		positiveIntVerifier(baseDeliveryTime, true,"PRAZO DE ENTREGA BASE");
	}

	protected void positiveIntVerifier(int i, boolean includeZero, String field) throws InvalidProductException{
		if(includeZero && i < 0)
			throw new InvalidProductException(field, "Deve ser um inteiro maior ou igual a zero");
		if(!includeZero && i <= 0){
			throw new InvalidProductException(field, "Deve ser um inteiro positvo");
		}
	}

	protected void nameVerifier(String name, String field) throws InvalidProductException{
		if(name == null || name.length() == 0)
			throw new InvalidProductException(field, "Deve ser não vazia");
	}

	void supplyNewCompany(SuppliedProduct sdp){
		_supliesInCompany.put(sdp.company().id(), sdp);
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
	public String toString(){
		return String.format(Locale.US, "%d - %s - %s - %s - %.2f - %.2f - %d - %d",
				_id, type(), _name, _category, (double) _basePrice,
				recommendPrice(), _baseDeliveryTime, totalUnits())
			+ extraString();
	}

}

