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
  private Client                    _client;


  //Constructor----------------------------------------------------------------------------------
  
  public Basket(Client client, Company cmp){
    _company = cmp;
    _client  = client;
  }

  //Getters--------------------------------------------------------------------------------------
  public Company company(){ return _company; }
  public TransportModality transportModality(){return _transport;}

  //---------------------------------------------------------------------------------------------
  
  void addService(AdditionalService addServ){
    //TODO
  }

  void setTransportModality(TransportModality tm){
    //TODO
  }

  Purchase checkout(int CurrDate, int purchaseSeld){
    //TODO
    Purchase p = new Purchase();
    return p;
  }

  int update(Product p, int quant){
    //TODO
    return 0;
  }

  public boolean canCheckout(){
    //TODO
    return true;
  }
  
  @Override
  public String toString(){
    //TODO
    return "";
  
  }

  
}
