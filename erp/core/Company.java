package erp.core;

import erp.core.exception.InvalidCompanyException;
import erp.core.exception.InvalidStockOperationException;
import erp.core.exception.ProductNotSuppliedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Company implements java.io.Serializable, Comparable<Company> {
	@java.io.Serial
	private static final long serialVersionUID = 202616112100L;
	private int                             _id;
	private String                          _name;
	private Map<Integer, SuppliedProduct>   _productSupplies;

	//Constructor----------------------------------------------------------------------------------
	public Company(int id, String name) throws InvalidCompanyException{
		nameVerifier(name);
		_id              = id;
		_name            = name;
		_productSupplies = new TreeMap<>();
	}
	
	//Getters--------------------------------------------------------------------------------------

	public int id(){ return  _id; }
	public String name(){ return _name; }
	
	public SuppliedProduct supply(Product prd) throws ProductNotSuppliedException {
		SuppliedProduct sp = _productSupplies.get(prd.id());
		if (sp == null || sp.units() == 0)
			throw new ProductNotSuppliedException(_id, prd.id());
		return sp;
	}
	public Collection<SuppliedProduct> availableProducts() {
		List<SuppliedProduct> availableProducts = new ArrayList<>(); 
		for(SuppliedProduct sp: _productSupplies.values()){
			if(sp.isAvailable())
				availableProducts.add(sp);
		}
		return availableProducts;
	}

	//---------------------------------------------------------------------------------------------


	private void nameVerifier(String name) throws InvalidCompanyException{
		if(name == null || name.length() == 0)
			throw new InvalidCompanyException();
	}

	void changeInventory(Product prd, int delta, double factor) throws InvalidStockOperationException{
		verifyFactor(factor);
		SuppliedProduct sp = _productSupplies.get(prd.id()); 
		//Not in
		if(sp == null){
			verifyDelta(delta - 1);// "-1" Makes 0 be excluded 
			_productSupplies.put(prd.id(), new SuppliedProduct(prd, this, factor, delta));
			return;
		}
		delta += sp.units();
		verifyDelta(delta);        
		sp.update(factor, delta);
	}
	
	private void verifyDelta(int delta) throws InvalidStockOperationException{
		if(delta < 0)
			throw new InvalidStockOperationException("UNIDADES", "O resultado da alteração da quantidade, não pode ser negativa");
	}
	
	private void verifyFactor(double factor) throws InvalidStockOperationException{
		if(factor <= 0)
			throw new InvalidStockOperationException("FATOR", "O número deve ser postivo");
	}

	@Override 
	public int compareTo(Company cmp){
		//The smallest
		return _id - cmp.id();
	}

	@Override 
	public String toString(){
		return String.format("%d - %s", _id, _name);
	}
}
