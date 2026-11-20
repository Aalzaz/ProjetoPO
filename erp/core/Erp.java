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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The whole ERP system: holds all the state and the domain logic.
 * <p>
 * It keeps the registries of products, companies, clients and purchases, the
 * current system date and the counters used to assign identifiers
 * automatically and incrementally. It also tracks whether there are changes
 * not yet saved, so that the application knows when to ask the user to save
 * before leaving the current state.
 * <p>
 * The class is serializable: the entire state of the application is saved and
 * recovered through Java serialization, and the identifier counters are part
 * of that state, so identifiers keep being assigned from the last value used
 * after a recovery.
 * <p>
 * This class is meant to be used only through the {@code ErpManager} facade
 * and has no user interaction code.
 */
class Erp implements Serializable {
	/** Serial version number for serialization. */
	@java.io.Serial
	private static final long serialVersionUID = 202608261200L;

	/** Whether the state has changed since it was last saved (or loaded). */
	private boolean 						_unsavedChanges;

	/** The current system date, in days (starts at day 1). */
	private int     						_date;

	/** The identifier to assign to the next registered product. */
	private int     						_nextProductId;

	/** The identifier to assign to the next registered company. */
	private int    							_nextCompanyId;

	/** The identifier to assign to the next purchase. */
	private int     						_nextPurchaSeld;

	/** All the products, indexed by product identifier. */
	private Map<Integer, Product> 			_products;

	/** All the companies, indexed by company identifier. */
	private Map<Integer, Company> 			_companies;

	/** All the purchases, indexed by purchase identifier. */
	private Map<Integer, Purchase>  		_purchases;

	/** All the clients, indexed by their e-mail address. */
	private Map<String, Client> 			_clients;

	/** The available product search criteria. */
	private final List<SearchCriteria> 		_searchCriteriaList = Arrays.asList(
        new NameCriteria(),
        new CategoryCriteria(),
        new OriginCriteria()
    );

	/** All the Transport modalities, indexed by name, in registration order. */
	private Map<String, TransportModality>	_transportModa;
	

	//Constructor----------------------------------------------------------------------------------

	/**
	 * Creates an empty system: no products, companies, clients or purchases,
	 * the date set to day 1, all identifier counters starting at 1 and no
	 * unsaved changes.
	 */
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
		_transportModa  = new LinkedHashMap<>();
	} 
	//Getters--------------------------------------------------------------------------------------

	/**
	 * @return true if the state has changed since it was last saved (or
	 *         loaded), false otherwise.
	 */
	boolean hasUnsavedChanges(){ return _unsavedChanges; }

	/**
	 * @return the current system date, in days.
	 */
	int date(){ return _date;}

	/**
	 * @return the identifier that will be assigned to the next registered
	 *         company.
	 */
	int nextCompanyId(){ return _nextCompanyId;}

	/**
	 * @return the identifier that will be assigned to the next purchase.
	 */
	int nextPurchaSeld(){ return _nextPurchaSeld;}

	/**
	 * @return the identifier that will be assigned to the next registered
	 *         product.
	 */
	int nextProductId(){ return _nextProductId;}

	/**
	 * @return all the products, ordered by their identifiers.
	 */
	Collection<Product> getProducts(){ return _products.values(); }

	/**
	 * @return all the companies, ordered by their identifiers.
	 */
	Collection<Company> getCompanies(){ return  _companies.values(); }

	/**
	 * Obtains the basket of a client in a company.
	 *
	 * @param email     the e-mail address (identifier) of the client.
	 * @param companyId the identifier of the company.
	 * @return the basket of the client in the company.
	 * @throws UnknownClientException  if there is no client with the given
	 *                                 e-mail address.
	 * @throws UnknownCompanyException if there is no company with the given
	 *                                 identifier.
	 */
	Basket getBasket(String email, int companyId) throws UnknownClientException, UnknownCompanyException{
		if(!_clients.containsKey(email))
			throw new UnknownClientException(email);
		if(!_companies.containsKey(companyId))
			throw new UnknownCompanyException(companyId);
		
		return _clients.get(email).basketInCompany(_companies.get(companyId));
	}

	/**
	 * @return all the clients, ordered lexicographically by name, ignoring
	 *         case, and, for clients with the same name, by e-mail address,
	 *         also ignoring case.
	 */
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

	/**
	 * Obtains a product by its identifier.
	 *
	 * @param id the identifier of the product.
	 * @return the product with the given identifier.
	 * @throws UnknownProductException if there is no product with the given
	 *                                 identifier.
	 */
	Product getProduct(int id) throws UnknownProductException{
		if(!_products.containsKey(id))
			throw new UnknownProductException(id);
		return _products.get(id);
	}

	
	/**
	 * Obtains a company by its identifier.
	 *
	 * @param id the identifier of the company.
	 * @return the company with the given identifier.
	 * @throws UnknownCompanyException if there is no company with the given
	 *                                 identifier.
	*/
	Company getCompany(int id) throws UnknownCompanyException{
		if (_companies.containsKey(id)) {
			return _companies.get(id);
		}
		throw new UnknownCompanyException(id);
	}
	
	/**
	 * Obtains a client by its e-mail address.
	*
	* @param email the e-mail address (identifier) of the client.
	* @return the client with the given e-mail address.
	* @throws UnknownClientException if there is no client with the given
	*                                e-mail address.
	*/
	Client getClient(String email) throws UnknownClientException{
		if (!_clients.containsKey(email)) 
			throw new UnknownClientException(email);
		return _clients.get(email);
	}
	
	/**
	 * @return the names of the available product search criteria.
	*/
	String[] searchCriteria() {
		String[] criteria = new String[_searchCriteriaList.size()];
		int j = 0;
		for (SearchCriteria sc : _searchCriteriaList) {
			criteria[j] = sc.name();
			j++;
		}
		return criteria;
	}
	
	/**
	 * @return the names of the available product types.
	*/
	String[] productTypes() {
		String[] vector = new String[ProductType.values().length];
		int j = 0;
		for (ProductType prdType : ProductType.values()) {
			vector[j] = prdType.toString();
			j++;
		}
		return vector;
	}
	
	/**
	 * @return the names of the available product categories.
	*/
	String[] productCategories() {
		String[] vector = new String[ProductCategory.values().length];
		int j = 0;
		for (ProductCategory prdCategory : ProductCategory.values()) {
			vector[j] = prdCategory.toString();
			j++;
		}
		return vector;
	}
	
	/**
	 * Obtains the products that a company makes available.
	 *
	 * @param id the identifier of the company.
	 * @return the products supplied by the company.
	 * @throws UnknownCompanyException if there is no company with the given
	 *                                 identifier.
	 */
	Collection<SuppliedProduct> availableProducts(int id) throws UnknownCompanyException{
		if(!_companies.containsKey(id))
			throw new UnknownCompanyException(id);
		return  _companies.get(id).availableProducts();
	}

	//---------------------------------------------------------------------------------------------
	
	/**
	 * Indicates whether products of a given type need the extra information
	 * about their origin (country of origin and customs tax).
	 *
	 * @param type the name of the product type.
	 * @return true if products of that type need origin information, false
	 *         otherwise.
	 * @throws IllegalArgumentException if there is no product type with the
	 *                                  given name.
	 */
	boolean productNeedsOrigin(String type) throws IllegalArgumentException{ 
		return ProductType.valueOf(type).productNeedsOrigin();
	}

	/**
	 * Searches the products according to a criterion and a term.
	 *
	 * @param criterion the name of the search criterion (compared ignoring
	 *                  case).
	 * @param term      the search term.
	 * @return the products that satisfy the criterion for the term, or an
	 *         empty collection if the criterion is unknown or no product is
	 *         found.
	 * @throws IllegalArgumentException if the criterion cannot be handled.
	 */
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

	/**
	 * Records that the state has changed and is no longer the same as the
	 * last saved one.
	*/
	void systemChanged(){
		_unsavedChanges = true;
	}
	
	/**
	 * Records that the current state has just been saved, so there are no
	 * unsaved changes.
	*/
	void systemWasSaved(){
		_unsavedChanges = false;
	}
	
	/**
	 * Advances the system date.
	 *
	 * @param date the number of days to add to the current date.
	 */
	void advanceDate(int date){
		_date+= date;
	}


	/**
	 * Changes the inventory of a product in a company: the units are added to
	 * the available ones and the price factor is replaced. This is also how a
	 * company starts supplying a product that is already registered. The
	 * operation only has effect if the amount and the factor are both valid.
	 *
	 * @param companyId the identifier of the company.
	 * @param productId the identifier of the product.
	 * @param units     the number of units to add (negative to remove).
	 * @param factor    the new multiplicative factor of the recommended price.
	 * @throws UnknownCompanyException      if there is no company with the
	 *                                      given identifier.
	 * @throws UnknownProductException      if there is no product with the
	 *                                      given identifier.
	 * @throws InvalidStockOperationException if the amount or the factor are
	 *                                      not valid.
	 */
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

	/**
	 * Registers a client. The e-mail address identifies the client, so it
	 * cannot be already in use.
	 *
	 * @param name  the name of the client.
	 * @param email the e-mail address of the client.
	 * @throws InvalidClientException if the data is not valid or the e-mail
	 *                                address is already in use.
	 */
	void registerClient(String name, String email) throws InvalidClientException{
		Client client = new Client(email, name);

		if(_clients.containsKey(email))
			throw new InvalidClientException(email, "Email em uso por outro cliente");

		_clients.put(email, client);
		systemChanged();

	}

	/**
	 * Registers a company. The identifier is assigned automatically.
	 *
	 * @param name the name of the company.
	 * @return the identifier assigned to the new company.
	 * @throws InvalidCompanyException if the data is not valid.
	 */
	int registerCompany(String name) throws InvalidCompanyException{
		Company comp = new Company(_nextCompanyId, name);
		_companies.put(comp.id(), comp);
		_nextCompanyId++;
		systemChanged();
		return comp.id();
	}

	/**
	 * Registers an imported product. The identifier is assigned
	 * automatically. The product is not supplied by any company until a
	 * company makes it available.
	 *
	 * @param name         the name/description of the product.
	 * @param basePrice    the base price of the product.
	 * @param category     the category of the product.
	 * @param baseDelay    the base delivery time of the product, in days.
	 * @param originCounty the country of origin of the product.
	 * @param customTax    the customs tax, as a percentage.
	 * @return the identifier assigned to the new product.
	 * @throws InvalidProductException if the data is not valid.
	 */
	int registerImportedProduct(String name, Integer basePrice, String category, Integer baseDelay, 
								String originCounty, Double customTax) throws InvalidProductException {
		Product prd = new ImportedProduct(_nextProductId, name, category, basePrice, baseDelay, originCounty, customTax);
		_products.put(prd.id(), prd);
		_nextProductId++;
		systemChanged();
		return prd.id();
	}

	/**
	 * Registers a national product. The identifier is assigned automatically.
	 * The product is not supplied by any company until a company makes it
	 * available.
	 *
	 * @param name             the name/description of the product.
	 * @param basePrice        the base price of the product.
	 * @param category         the category of the product.
	 * @param baseDeliveryTime the base delivery time of the product, in days.
	 * @return the identifier assigned to the new product.
	 * @throws InvalidProductException if the data is not valid.
	 */
	int registerNationalProduct(String name, int basePrice, String category,  int baseDeliveryTime) 
								throws InvalidProductException{
		Product prd = new NationalProduct(_nextProductId, name, category, basePrice, baseDeliveryTime);
		_products.put(prd.id(), prd);
		_nextProductId++;
		systemChanged();
		return prd.id();
	}

	/**
	 * Registers a shipping modality. If a modality with the same name already
	 * exists, its fee and transport time are replaced.
	 *
	 * @param modalityName the name of the modality.
	 * @param price        the transport fee.
	 * @param time         the transport time, in days.
	 */
	void registerShippingRate(String modalityName, int price, int time){
		_transportModa.put(modalityName, new TransportModality(modalityName, price, time));
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
