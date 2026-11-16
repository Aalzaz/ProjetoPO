package erp.core;

import erp.core.exception.*;
import erp.core.exception.parserExceptions.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;

/**
 * The whole ERP system: holds all the state and the domain logic.
 */
class Erp implements Serializable {
	@java.io.Serial
	private static final long serialVersionUID = 202608261200L;
	private boolean 				_modified;
	private int     				_date;
	private int     				_nextProductId;
	private int    					_nextCompanyId;
	private int     				_nextPurchaSeld;
	private Map<Integer, Product> 	_products;
	private Map<Integer, Company> 	_companies;
	private Map<Integer, Purchase>  _purchases;
	private Map<String, Client> 	_clients;


	//Constructor----------------------------------------------------------------------------------

	public Erp(){
		_nextCompanyId  = 1;
		_nextProductId  = 1;
		_nextPurchaSeld = 1;
		_date           = 1;
		_modified       = false;
		_products       = new TreeMap<>();
		_companies      = new TreeMap<>();
		_purchases      = new TreeMap<>();
		_clients		= new HashMap<>();
	} 

	public Erp(int prodId, int compId, int sellId, int date,
				Map<Integer, Product> prods, Map<Integer, Company> comps, 
				Map<Integer, Purchase> purchs, Map<String,Client> clients){
		_nextCompanyId = compId;
		_nextProductId  = prodId;
		_nextPurchaSeld = sellId;
		_date           = date;
		_modified       = true;
		_products       = new TreeMap<>(prods);
		_companies      = new TreeMap<>(comps);
		_purchases      = new TreeMap<>(purchs);
		_clients		= new HashMap<>(clients);
		
	}

	//Getters--------------------------------------------------------------------------------------

	public boolean modified(){ return _modified; }
	public int date(){ return _date;}
	public int nextCompanyId(){ return _nextCompanyId;}
	public int nextPurchaSeld(){ return _nextPurchaSeld;}
	public int nextProductId(){ return _nextProductId;}

	//---------------------------------------------------------------------------------------------

	void nextDay(){}//TODO

	void updateStock(int companyId, int productId, int units, double factor) 
						throws UnknownCompanyException, UnknownProductException{


	}

	void registerClient(String name, String email) throws InvalidClient{
		Client client = new Client(email, name);

		if(_clients.containsKey(name))
			throw new InvalidClient(email, "Email em uso por outro cliente");

		_clients.put(name, client);

	}

	void registerCompany(String name) throws InvalidCompany{
		Company comp = new Company(_nextCompanyId, name);
		_companies.put(comp.id(), comp);
		_nextCompanyId++;
	}
	void registerImportedProduct(String name, Integer basePrice, ProductCategory category, Integer baseDelay, String originCounty, Double customTax) throws InvalidProduct{
		Product prd = new ImportedProduct(_nextProductId, name, category, basePrice, baseDelay, originCounty, customTax);
		_products.put(prd.id(), prd);
		_nextProductId++;

	}

	void registerNationalProduct(String name, int basePrice, ProductCategory category,  int baseDeliveryTime) throws InvalidProduct{
		Product prd = new NationalProduct(_nextProductId, name, category, basePrice, baseDeliveryTime);
		_products.put(prd.id(), prd);
	}
	

	/**
	 * Read text input file at the beginning of the program and populates the
	 * the state of this application with the domain entities represented in the text file.
	 * 
	 * @param filename name of the text input file to process
	 * @throws UnrecognizedEntryException if some entry is not correct
	 * @throws IOException if there is an IO erro while processing the text file
	 **/
	void importFile(String filename) throws UnrecognizedEntryException, IOException
						/* FIXME maybe other exceptions */ {
		//FIXME implement method
	}
}
