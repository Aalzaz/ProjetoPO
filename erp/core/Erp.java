package erp.core;

import erp.core.exception.*;


import java.io.IOException;
import java.io.Serializable;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The whole ERP system: holds all the state and the domain logic.
 */
class Erp implements Serializable {
	@java.io.Serial
	private static final long serialVersionUID = 202608261200L;
	private boolean 					_unsavedChanges;
	private int     					_date;
	private int     					_nextProductId;
	private int    						_nextCompanyId;
	private int     					_nextPurchaSeld;
	private Map<Integer, Product> 		_products;
	private Map<Integer, Company> 		_companies;
	private Map<Integer, Purchase>  	_purchases;
	private Map<String, Client> 		_clients;
	private final List<SearchCriteria> 	_searchCriteriaList = Arrays.asList(
        new NameCriteria(),
        new CategoryCriteria(),
        new OriginCriteria()
    );
	

	//Constructor----------------------------------------------------------------------------------

	Erp(){
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

	boolean hasUnsavedChanges(){ return _unsavedChanges; }

	int date(){ return _date;}

	int nextCompanyId(){ return _nextCompanyId;}

	int nextPurchaSeld(){ return _nextPurchaSeld;}

	int nextProductId(){ return _nextProductId;}

	Collection<Product> getProducts(){ return _products.values(); }

	Collection<Company> getCompanies(){ return  _companies.values(); }

	Basket getBasket(String email, int companyId) throws UnknownClientException, UnknownCompanyException{
		if(!_clients.containsKey(email))
			throw new UnknownClientException(email);
		if(!_companies.containsKey(companyId))
			throw new UnknownCompanyException(companyId);
		
		return _clients.get(email).basketInCompany(_companies.get(companyId));
	}

	public Collection<Client> getClients() {
		List<Client> clientsList = new ArrayList<>(_clients.values());
		clientsList.sort(new Comparator<Client>() {
			@Override
			public int compare(Client c1, Client c2) {
				int nameComparison = c1.name().compareToIgnoreCase(c2.name());
				if (nameComparison != 0) {
					return nameComparison;
				}
				return c1.email().compareToIgnoreCase(c2.email());
			}
		});
		return clientsList;
	}

	Product getProduct(int id) throws UnknownProductException{
		if(!_products.containsKey(id))
			throw new UnknownProductException(id);
		return _products.get(id);
	}

	boolean productNeedsOrigin(String type) throws IllegalArgumentException{ 
		return ProductType.valueOf(type).productNeedsOrigin();
	}



	Collection<Product> searchProducts(String criterion, String term)
								throws IllegalArgumentException {
		Collection<Product> collection = new ArrayList<>();
		SearchCriteria criteria = null;
		for (SearchCriteria sc : _searchCriteriaList) {
			if(sc.name().equalsIgnoreCase(criterion)){
				criteria = sc;
				break;
			}
		}
		if(criteria == null)
			return Collections.emptyList();
		for (Product product : _products.values()) {
			if(criteria.matches(product, term))
				collection.add(product);
		}
		return collection;
	}

	Company getCompany(int id) throws UnknownCompanyException{
		if (_companies.containsKey(id)) {
			return _companies.get(id);
		}
		throw new UnknownCompanyException(id);
	}

	Client getClient(String email) throws UnknownClientException{
		if (!_clients.containsKey(email)) 
			throw new UnknownClientException(email);
		return _clients.get(email);
	}

	String[] searchCriteria() {
		String[] criteria = new String[_searchCriteriaList.size()];
		int j = 0;
		for (SearchCriteria sc : _searchCriteriaList) {
			criteria[j] = sc.name();
			j++;
		}
		return criteria;
	}

	String[] productTypes() {
		String[] vector = new String[ProductType.values().length];
		int j = 0;
		for (ProductType prdType : ProductType.values()) {
			vector[j] = prdType.toString();
			j++;
		}
		return vector;
	}
	
	String[] productCategories() {
		String[] vector = new String[ProductCategory.values().length];
		int j = 0;
		for (ProductCategory prdCategory : ProductCategory.values()) {
			vector[j] = prdCategory.toString();
			j++;
		}
		return vector;
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
	Collection<SuppliedProduct> availableProducts(int id) throws UnknownCompanyException{
		if(!_companies.containsKey(id))
			throw new UnknownCompanyException(id);
		return  _companies.get(id).availableProducts();
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

	int registerCompany(String name) throws InvalidCompanyException{
		Company comp = new Company(_nextCompanyId, name);
		_companies.put(comp.id(), comp);
		_nextCompanyId++;
		systemChanged();
		return comp.id();
	}

	int registerImportedProduct(String name, Integer basePrice, String category, Integer baseDelay, 
								String originCounty, Double customTax) throws InvalidProductException {
		Product prd = new ImportedProduct(_nextProductId, name, category, basePrice, baseDelay, originCounty, customTax);
		_products.put(prd.id(), prd);
		_nextProductId++;
		systemChanged();
		return prd.id();
	}

	int registerNationalProduct(String name, int basePrice, String category,  int baseDeliveryTime) 
								throws InvalidProductException{
		Product prd = new NationalProduct(_nextProductId, name, category, basePrice, baseDeliveryTime);
		_products.put(prd.id(), prd);
		_nextProductId++;
		systemChanged();
		return prd.id();
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
