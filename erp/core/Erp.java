package erp.core;

import erp.core.exception.*;


import java.io.IOException;
import java.io.Serializable;
import java.util.TreeMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The whole ERP system: holds all the state and the domain logic.
 */
class Erp implements Serializable {
	@java.io.Serial
	private static final long serialVersionUID = 202608261200L;
	private boolean 				_unsavedChanges;
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
		_unsavedChanges = false;
		_products       = new TreeMap<>();
		_companies      = new TreeMap<>();
		_purchases      = new TreeMap<>();
		_clients		= new HashMap<>();
	} 
	//Getters--------------------------------------------------------------------------------------

	public boolean hasUnsavedChanges(){ return _unsavedChanges; }
	public int date(){ return _date;}
	public int nextCompanyId(){ return _nextCompanyId;}
	public int nextPurchaSeld(){ return _nextPurchaSeld;}
	public int nextProductId(){ return _nextProductId;}

	Collection<Product> getProducts(){ return _products.values(); }

	Product getProduct(int id) throws UnknownProductException{
		if(!_products.containsKey(id))
			throw new UnknownProductException(id);
		return _products.get(id);
	}


	Client getClient(String email) throws UnknownClientException{
		if (!_clients.containsKey(email)) 
			throw new UnknownClientException(email);
		return _clients.get(email);
	}

	//---------------------------------------------------------------------------------------------
	private void systemChanged(){
		_unsavedChanges = true;
	}

	void systemWasSaved(){
		_unsavedChanges = false;
	}

	void advanceDate(int date){
		_date+= date;
	}

	void updateStock(int companyId, int productId, int units, double factor) 
						throws UnknownCompanyException, UnknownProductException, InvalidStockOperationException{
		if(!_companies.containsKey(companyId))
			throw new UnknownCompanyException(companyId);
		if(!_products.containsKey(productId))
			throw new UnknownProductException(productId);
		Company cmp = _companies.get(companyId);
		Product prd = _products.get(productId);
		cmp.changeInventory(prd, units, factor);
		systemChanged();
	}

	void registerClient(String name, String email) throws InvalidClientException{
		Client client = new Client(email, name);

		if(_clients.containsKey(email))
			throw new InvalidClientException(email, "Email em uso por outro cliente");

		_clients.put(email, client);
		systemChanged();

	}

	void registerCompany(String name) throws InvalidCompanyException{
		Company comp = new Company(_nextCompanyId, name);
		_companies.put(comp.id(), comp);
		_nextCompanyId++;
		systemChanged();
	}
	void registerImportedProduct(String name, Integer basePrice, ProductCategory category, Integer baseDelay, 
								String originCounty, Double customTax) throws InvalidProductException {
		Product prd = new ImportedProduct(_nextProductId, name, category, basePrice, baseDelay, originCounty, customTax);
		_products.put(prd.id(), prd);
		_nextProductId++;
		systemChanged();

	}

	void registerNationalProduct(String name, int basePrice, String category,  int baseDeliveryTime) 
								throws InvalidProductException{
		Product prd = new NationalProduct(_nextProductId, name, category, basePrice, baseDeliveryTime);
		_products.put(prd.id(), prd);
		_nextProductId++;
		systemChanged();
	}
	

	/**
	 * Read text input file at the beginning of the program and populates the
	 * the state of this application with the domain entities represented in the text file.
	 * 
	 * @param filename name of the text input file to process
	 * @param Parser initialized with the ErpManager
	 * @throws UnrecognizedEntryException if some entry is not correct
	 * @throws IOException if there is an IO erro while processing the text file
	 **/
	void importFile(String filename, Parser parser) throws UnrecognizedEntryException, IOException {
		parser.parserFile(filename);
		systemChanged();
	}
}
