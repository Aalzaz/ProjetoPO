package erp.core;

import erp.core.exception.*;


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
		_nextPurchaSeld	= 1;
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
		_nextCompanyId  = compId;
		_nextProductId  = prodId;
		_nextPurchaSeld = sellId;
		_date           = date;
		_modified       = true;
		_products       = new TreeMap<>(prods);
		_companies      = new TreeMap<>(comps);
		_purchases      = new TreeMap<>(purchs);
		_clients        = new HashMap<>(clients);
		
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
						throws UnknownCompanyException, UnknownProductException, InvalidStockOperationException{
		if(!_companies.containsKey(companyId))
			throw new UnknownCompanyException(companyId);
		if(!_products.containsKey(productId))
			throw new UnknownProductException(productId);
		Company cmp = _companies.get(companyId);
		Product prd = _products.get(productId);
		cmp.changeInventory(prd, units, factor);
	}

	void registerClient(String name, String email) throws InvalidClientException{
		Client client = new Client(email, name);

		if(_clients.containsKey(email))
			throw new InvalidClientException(email, "Email em uso por outro cliente");

		_clients.put(email, client);

	}

	void registerCompany(String name) throws InvalidCompanyException{
		Company comp = new Company(_nextCompanyId, name);
		_companies.put(comp.id(), comp);
		_nextCompanyId++;
	}
	void registerImportedProduct(String name, Integer basePrice, ProductCategory category, Integer baseDelay, 
								String originCounty, Double customTax) throws InvalidProductException {
		Product prd = new ImportedProduct(_nextProductId, name, category, basePrice, baseDelay, originCounty, customTax);
		_products.put(prd.id(), prd);
		_nextProductId++;

	}

	void registerNationalProduct(String name, int basePrice, ProductCategory category,  int baseDeliveryTime) 
								throws InvalidProductException{
		Product prd = new NationalProduct(_nextProductId, name, category, basePrice, baseDeliveryTime);
		_products.put(prd.id(), prd);
		_nextProductId++;
	}
	

	/**
	 * Read text input file at the beginning of the program and populates the
	 * the state of this application with the domain entities represented in the text file.
	 * 
	 * @param filename name of the text input file to process
	 * @throws UnrecognizedEntryException if some entry is not correct
	 * @throws IOException if there is an IO erro while processing the text file
	 **/
	void importFile(String filename, Parser parser) throws UnrecognizedEntryException, IOException {
		parser.parserFile(filename);
	}
}
