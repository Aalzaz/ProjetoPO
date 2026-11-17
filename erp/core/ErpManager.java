package erp.core;

import erp.core.exception.*;



import java.io.*;
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

	//FIXME maybe define constructors
	// --- Getters -----------------------------------------------------------
	public int getDate () {
		return _erp.date();
	}
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
			throw new MissingResourceException("O filepath é vazio/nulo", "FILEPATH", "");
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
	public void load(String filename) throws IOException, ClassNotFoundException {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
        Erp loaded 	= (Erp) in.readObject();
        _erp 		= loaded;     
        _filePath 	= filename;
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

	public void advanceDate(int days){
		_erp.advanceDate(days);
	}

	public void registerCompany(String name) throws InvalidCompanyException{
		_erp.registerCompany(name);
	}
	public void registerNationalProduct(String name, int basePrice, ProductCategory category,  int baseDeliveryTime) throws InvalidProductException{
		_erp.registerNationalProduct(name, basePrice, category,  baseDeliveryTime);
	}

	public void registerImportedProduct(String name, Integer basePrice, ProductCategory category, Integer baseDelay, String originCounty, Double customTax) throws InvalidProductException{
		_erp.registerImportedProduct(name, basePrice, category, baseDelay, originCounty, customTax);
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
