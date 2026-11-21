package erp.core.exception;

public class UnknownProductInBasketException extends Exception{
	@java.io.Serial 
	private static final long serialVersionUID = 202611201535L;
	private int _productId;

	public UnknownProductInBasketException(int id){
		_productId =id;
	}

	public int productId(){ return _productId; }
}