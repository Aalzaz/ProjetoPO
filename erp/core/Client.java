package erp.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import erp.core.exception.InvalidClientException;


public class Client implements Serializable{
	@java.io.Serial
	private static final long serialVersionUID = 202616112100L;
	private String                  _email;
	private String                  _name;
	private double                  _totalSpent;
	private Map<Integer, Basket>    _baskets;

//Constructor----------------------------------------------------------------------------------
	public Client(String email, String name) throws InvalidClientException{
		verify(name, email);
		_name    = name;
		_email   = email;
		_baskets = new HashMap<>();
	}
//Getters--------------------------------------------------------------------------------------

	public Basket basketInCompany(Company cmp) { return _baskets.get(cmp.id()); }
	public String name(){ return _name; }
	public String email(){ return _email;}
	public double totalSpent(){ return _totalSpent; }

//---------------------------------------------------------------------------------------------
	private void verify(String name, String email) throws InvalidClientException{
		stringVerifier(name);
		stringVerifier(email);
	}

	private void stringVerifier(String name) throws InvalidClientException{
		if(name == null || name.length() == 0)
			throw new InvalidClientException("NOME", "Não dever ser vazio/nulo");
	}


	void debit(double val){
		_totalSpent += val;
	}
	boolean addBasket(Basket basket) {
		if(_baskets.containsKey(basket.company().id()))
			return false;
	_baskets.put(basket.company().id(), basket);
	return true;
	}
	

	@Override 
	public String toString(){
		return String.format(Locale.US, "%s - %s - %.2f", _email, _name, _totalSpent);
	}
	
}
