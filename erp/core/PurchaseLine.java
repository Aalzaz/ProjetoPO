package erp.core;

import java.io.Serializable;

public class PurchaseLine implements Serializable{
	@java.io.Serial
	private static final long serialVersionUID = 202620111800L;
	private Product _product;
	private int 	_quantity;
	private double 	_unitPrice;

	public PurchaseLine(Product prd, int quant, double unitPrice){
		_product = 		prd;
		_quantity = 	quant;
		_unitPrice = 	unitPrice;
	}

	public double totalPrice(){ return _quantity * unitPrice; }

	@Override 
	public String toString(){
		//TODO
	}
}
