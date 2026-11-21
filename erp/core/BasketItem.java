package erp.core;

import java.io.Serializable;

public class BasketItem implements Serializable {
	@java.io.Serial
	private static final long serialVersionUID = 202608261200L;
	private  int 		_quant;
	private  Product 	_prd;

//Constructor-------------------------------------------------------------------

	public BasketItem(int quant, Product prd){
		_quant 	= quant;
		_prd 	= prd;
	}

//Getter------------------------------------------------------------------------

	public int quantity(){ return _quant; }

	public double unitPrice(Company cmp ){ return  _prd.recommendPrice() * _prd.suplyInCompany(cmp).factor(); }

	public double totalPrice(Company cmp ){ return  unitPrice(cmp) * _quant; }

//------------------------------------------------------------------------------

	void update(int delta){
		_quant += delta;
	}

}
