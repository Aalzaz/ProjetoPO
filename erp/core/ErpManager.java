package erp.core;

import erp.core.exception.*;



import java.io.*;
import java.util.Collection;
import java.util.MissingResourceException;

/**
 * The façade of the ERP core: the single entry point through which the
 * application interacts with the domain.
 *
 * <p><b>Façade</b> pattern.</p>
 */
public class ErpManager {

	/** The object doing all the actual work. */
	private Erp _erp = new Erp();
	private String _filePath;

	// --- Getters -----------------------------------------------------------

	public int getDate () { return _erp.date(); }

	public Collection<Product> getProducts(){ return _erp.getProducts(); }

	public Product getProduct(int id) throws UnknownProductException{ return _erp.getProduct(id); }

	public Client getClient(String email) throws UnknownClientException{ return _erp.getClient(email); }

	public boolean hasUnsavedChanges(){ return _erp.hasUnsavedChanges(); }

	public String[] productTypes(){ return _erp.productTypes(); }

	public String[] productCategories(){ return _erp.productCategories(); }

	public String[] searchCriteria(){ return _erp.searchCriteria(); }

	public Collection<Product> searchProducts(String criterion, String term){ return _erp.searchProducts(criterion, term); }

	public Company getCompany(int id) throws UnknownCompanyException{ return _erp.getCompany(id); }

	public Collection<Company> getCompanies() {return _erp.getCompanies();}

	public Collection<SuppliedProduct> availableProducts(int id) throws UnknownCompanyException{ return _erp.availableProducts(id); }

	public Collection<Client> getClients(){ return _erp.getClients(); }

	public Basket getBasket(String email, int companyId) throws UnknownClientException, UnknownCompanyException { return _erp.getBasket(email, companyId); }

	// --- persistence -------------------------------------------------------

	/**
	 * Saves the serialized application's state into the file associated to the current Erp
	 * application
	 *
	 * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
	 * @throws MissingFileAssociationException if the current library does not have a file.
	 * @throws IOException if there is some error while serializing the state of the network to disk.
	 **/
	public void save() throws MissingFileAssociationException, IOException {
		if (_filePath == null) 
			throw new MissingFileAssociationException();
		writeState(_filePath);
		_erp.systemWasSaved();
	}

	private void writeState(String filePath) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
			out.writeObject(_erp);
    }
}

	/**
	 * Saves the serialized application's state into the specified file. The current Erp
	 * application is associated to this file.
	 *
	 * @param filename the name of the file.
	 * @throws FileNotFoundException if for some reason the file cannot be created or opened.
	 * @throws MissingFileAssociationException if the current library does not have a file.
	 * @throws IOException if there is some error while serializing the state of the network to disk.
	 **/
	public void saveAs(String filename) throws MissingFileAssociationException, IOException {
		_filePath = filename;
		save();
	}

	/**
	 * Loads the previously serialized application's state as set it as the current Erp
	 * application.
	 *
	 * @param filename name of the file containing the serialized application's state
	 *        to load.
	 * @throws UnavailableFileException if the specified file does not exist or there is
	 *         an error while processing this file.
	 **/
	public void load(String filename) throws UnavailableFileException {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
        Erp loaded 	= (Erp) in.readObject();
        _erp 		= loaded;     
        _filePath 	= filename;
    } catch (IOException | ClassNotFoundException e){
		throw new UnavailableFileException(filename);
	}
}

	/**
	 * Read text input file and initializes the current application (which should be empty)
	 * with the domain entities representeed in the import file.
	 *
	 * @param datafile name of the text input file
	 * @throws ImportFileException if some error happens during the processing of the
	 * import file.
	 **/
	public void importFile(String filename) throws ImportFileException {
		try {
		if (filename != null && !filename.isEmpty()){
			Parser parser = new Parser(this);
			_erp.importFile(filename, parser);
			}
		} catch (IOException | UnrecognizedEntryException e) {
		throw new ImportFileException(filename, e);
		}
	}
//---------------------------------------------------------------------------------------------------------

	public void advanceDate(int days){ _erp.advanceDate(days); }

	public int registerCompany(String name) throws InvalidCompanyException{ return _erp.registerCompany(name); }

	public boolean productNeedsOrigin(String type) throws IllegalArgumentException{ return  _erp.productNeedsOrigin(type); }

	public int registerNationalProduct(String name, int basePrice, String category,  int baseDeliveryTime) throws InvalidProductException{
		return _erp.registerNationalProduct(name, basePrice, category,  baseDeliveryTime);
	}

	public int registerImportedProduct(String name, Integer basePrice, String category, Integer baseDelay, String originCounty, Double customTax) throws InvalidProductException{
		return _erp.registerImportedProduct(name, basePrice, category, baseDelay, originCounty, customTax);
	}
	public void registerClient(String name, String email) throws InvalidClientException{
		_erp.registerClient(name, email);
	}
	public void updateStock(int companyId, int productId, int units, double factor) 
						throws UnknownCompanyException, UnknownProductException, InvalidStockOperationException{
		_erp.updateStock(companyId, productId, units, factor);
	}
	public void registeShippingRate(String modalityName, int price, int time){
		//TODO
	}
}
