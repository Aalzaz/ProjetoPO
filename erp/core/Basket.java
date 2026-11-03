package erp.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A client's shopping basket at one company: what it intends to buy, and how it
 * wants the purchase shipped.
 *
 * <p>The basket menu is opened <em>over</em> a basket, so this class is the
 * receiver of every command in {@code erp.app.basket}. Its name and package are
 * fixed by that code; its content is yours to define.</p>
 */
public class Basket implements Serializable {
  @java.io.Serial
  private static final long serialVersionUID = 202608261200L;
  private Company                   _company;
  private Map<Product, BasketItem>  _items;
  private List<AdditionalService>   _services;
  private TransportModality         _transport;
  private Purchase                  _purchase;

  


  public Company company(){
    return _company;
  }


  
}
