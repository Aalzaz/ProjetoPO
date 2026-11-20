package erp.core.exception;

public class ProductNotInBasketException extends Exception{
	@java.io.Serial
	private static final long serialVersionUID = 202616112100L;
	private final int _id;

	public ProductNotInBasketException (int id) {
		_id = id;
	}

	public int id() {
		return  _id;
	}

}
