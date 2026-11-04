package erp.core;

import erp.core.exception.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.TreeMap;


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
    private TreeMap<Product, Product> _products;
    private TreeMap<Company, Company> _companys;
    private TreeMap<Purchase, Purchase> _purchases;


  //Constructor----------------------------------------------------------------------------------

  //Getters--------------------------------------------------------------------------------------

  public boolean modified(){ return _modified; }
  public int date(){ return _date;}
  public int nextCompanyId(){ return _nextCompanyId;}
  public int nextPurchaSeld(){ return _nextPurchaSeld;}
  public int nextProductId(){ return _nextProductId;}

  //---------------------------------------------------------------------------------------------

  void nextDay(){}//TODO
  void registerClient(String name, String email){}//TODO

  void registerNationalProduct(String name, ProductCategory category, int basePrice, int baseDeliveryTime){
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
