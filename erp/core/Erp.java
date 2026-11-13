package erp.core;

import erp.core.exception.*;
import erp.core.exception.parserExceptions.IntegerException;
import erp.core.exception.parserExceptions.StringException;

import java.io.IOException;
import java.io.Serializable;
import java.util.TreeMap;
import java.util.Map;

/**
 * The whole ERP system: holds all the state and the domain logic.
 */
class Erp implements Serializable {
	@java.io.Serial
	private static final long serialVersionUID = 202608261200L;
	private boolean _modified;
	private int     _date;
	private int     _nextProductId;
	private int     _nextCompanyId;
	private int     _nextPurchaSeld;
	private Map<Product, Product> _products;
	private Map<Company, Company> _companies;
	private Map<Purchase, Purchase> _purchases;


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
	} 

	public Erp(int prodId, int compId, int sellId, int date, Map<Product, Product> prods, Map<Company, Company> comps, Map<Purchase, Purchase> purchs){
		_nextCompanyId = compId;
		_nextProductId  = prodId;
		_nextPurchaSeld = sellId;
		_date           = date;
		_modified       = true;
		_products       = new TreeMap<>(prods);
		_companies      = new TreeMap<>(comps);
		_purchases      = new TreeMap<>(purchs);
		
	}

	//Getters--------------------------------------------------------------------------------------

	public boolean modified(){ return _modified; }
	public int date(){ return _date;}
	public int nextCompanyId(){ return _nextCompanyId;}
	public int nextPurchaSeld(){ return _nextPurchaSeld;}
	public int nextProductId(){ return _nextProductId;}

	//---------------------------------------------------------------------------------------------

	void nextDay(){}//TODO
	void registerClient(String name, String email){}//TODO

	void registerCompany(String name) throws StringException{
		Company comp = new Company(_nextCompanyId, name);
		_companies.put(comp, comp);
		_nextCompanyId++;
	}
	void registerImportedProduct(String name, Integer basePrice, ProductCategory category, Integer baseDelay, String originCounty, Double customTax) 
									throws StringException, IntegerException{
		Product prd = new ImportedProduct(_nextProductId, name, category, basePrice, baseDelay, originCounty, customTax);
		_products.put(prd, prd);
		_nextProductId++;

	}

	void registerNationalProduct(String name, int basePrice, ProductCategory category,  int baseDeliveryTime) throws StringException, IntegerException{
		Product prd = new NationalProduct(_nextProductId, name, category, basePrice, baseDeliveryTime);
		_products.put(prd, prd);
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
