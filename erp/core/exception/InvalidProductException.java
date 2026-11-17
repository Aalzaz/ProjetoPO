package erp.core.exception;

public class InvalidProductException extends ParserException{
	@java.io.Serial
	private static final long serialVersionUID = 202616112100L;
	private String _field;
	private String _correction;
	
	public InvalidProductException(String field, String correction){
		_field = field;
		_correction = correction;
	}

	@Override
	public String getMessage() {
		return "Campo: " + _field + " - " + _correction + ".";
	}
}
