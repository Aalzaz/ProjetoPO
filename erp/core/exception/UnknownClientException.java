package erp.core.exception;

public class UnknownClientException extends Exception{
	@java.io.Serial
	private static final long serialVersionUID = 202610272000L;
	private final String _email;

	public UnknownClientException(String email) { 
		_email  = email;
	}
	public String email() { 
		return _email;
	}
}
