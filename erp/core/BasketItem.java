package erp.core;

import java.io.Serializable;

public class BasketItem implements Serializable {
	@java.io.Serial
	private static final long serialVersionUID = 202608261200L;
	private Product _product;
	private int _quantity;

	//Getters ------------------------------------------------------------------------
	public Product getProduct () { return _product; }
	public int getQuant () { return _quantity; }

	public void addQuant (int quantity) {
		_quantity += quantity;
	}
}
