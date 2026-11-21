package erp.core;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> refs/remotes/origin/master
import java.util.Map;
import java.util.TreeMap;

import erp.core.exception.ProductNotSuppliedException;
import erp.core.exception.SupplyLimitException;
import erp.core.exception.UnknownProductInBasketException;

import erp.app.exception.NotEnoughInventoryException;
import erp.core.exception.ProductNotInBasketException;
import erp.core.exception.ProductNotSuppliedException;

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
<<<<<<< HEAD
	private Map<Integer, BasketItem>  _items    = new TreeMap<>();
	private List<AdditionalService>   _services = new ArrayList<>();
=======
	private Map<Integer, BasketItem>  _items;
	private List<AdditionalService>   _services;
>>>>>>> refs/remotes/origin/master
	private TransportModality         _transport;
	private Client                    _client;


	//Constructor----------------------------------------------------------------------------------
	
	public Basket(Client client, Company cmp){
	_company = cmp;
	_client  = client;
	}

	//Getters--------------------------------------------------------------------------------------
	public Company company(){ return _company; }
	
<<<<<<< HEAD
	public TransportModality transportModality(){return _transport; }
=======
	public TransportModality transportModality(){return _transport;}
	
	public BasketItem getItem(int id) throws ProductNotInBasketException{
		if (_items.containsKey(id) == false) {
			throw new ProductNotInBasketException(id);
		}
		return _items.get(id);
	}
>>>>>>> refs/remotes/origin/master

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
<<<<<<< HEAD

	void update(Product prd, int delta) throws ProductNotSuppliedException, SupplyLimitException, UnknownProductInBasketException{
		if(delta > _company.supply(prd).units())
			throw new SupplyLimitException(_company.id(), prd.id());
		if(!_items.containsKey(prd.id())){
			if(delta < 0)
				throw new UnknownProductInBasketException(prd.id());
			else
				_items.put(prd.id(), new BasketItem(delta, prd));
		}else
			if(-delta >= _items.get(prd.id()).quantity())
				_items.remove(prd.id());
			else			
				_items.get(prd.id()).update(delta);
=======
/**
 * Recieves a product id and a quantity, if the said quantity is positive, it adds that to the quantity
 * of the item in the basket, negative quantities remove, and a 0 removes the item entirely from 
 * the basket. In the case that the product was not in the basket, the program throws and exception,
 * if the company no longer supplies the product another is thrown, and finally if the company does
 * not have enough supply remaining, the final exception is thrown out.
 * 
 * @param id id of the product to have its quantity changed;
 * @param quant quantity of product to be updated from the basket;
 * @return new quantity of the product in the basket;
 * @throws ProductNotInBasketException if the indicated product was not in the basket;
 * @throws ProductNotSuppliedException if the indicated product was not supplied by the company;
 * @throws NotEnoughInventoryException in the company's supply of the product was not sufficient;
 */
	public int update(int id, int quant) throws ProductNotInBasketException, ProductNotSuppliedException, NotEnoughInventoryException{
		try {
			BasketItem item = getItem(id);
			if (quant > 0) {
				if (item.getQuant() + quant > _company.supply(item.getProduct()).units()) {
					throw new NotEnoughInventoryException(id);
				}
				item.addQuant(quant);
				return item.getQuant();
			}
			if (quant < 0) {
				if (-quant > item.getQuant()) {
					return -1;
				}
				item.addQuant(quant);
				if (item.getQuant() == 0) {
					_items.remove(id);
					return -2;
				}
				return item.getQuant();
			}
			_items.remove(id);
			return -2;
		}
		catch (ProductNotInBasketException e) {
			throw new ProductNotInBasketException(id);
		}
		catch (ProductNotSuppliedException e) {
			throw new ProductNotSuppliedException(id, _company.id());
		}
>>>>>>> refs/remotes/origin/master
	}

	public boolean canCheckout(){
	//TODO
	return true;
	}
	
	@Override
	public String toString(){
	//TODO
<<<<<<< HEAD
	return String.format(Locale.US,"%s - %d - %s - %s %.2f", _client.email(), _company.id(), _transport, );
=======
		return "";
>>>>>>> refs/remotes/origin/master
	}

	
}