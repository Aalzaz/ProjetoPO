package erp.core;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import erp.core.exception.ProductNotSuppliedException;
import erp.core.exception.SupplyLimitException;
import erp.core.exception.UnknownProductInBasketException;

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
	private Map<Integer, BasketItem>  _items    = new TreeMap<>();
	private List<AdditionalService>   _services = new ArrayList<>();
	private TransportModality         _transport;
	private Client                    _client;


	//Constructor----------------------------------------------------------------------------------
	
	public Basket(Client client, Company cmp){
	_company = cmp;
	_client  = client;
	}

	//Getters--------------------------------------------------------------------------------------
	public Company company(){ return _company; }
	
	public TransportModality transportModality(){return _transport; }

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
	}

	public boolean canCheckout(){
	//TODO
	return true;
	}
	
	@Override
	public String toString(){
	//TODO
	return String.format(Locale.US,"%s - %d - %s - %s %.2f", _client.email(), _company.id(), _transport, );
	}

	
}
