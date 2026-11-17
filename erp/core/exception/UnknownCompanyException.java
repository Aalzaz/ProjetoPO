package erp.core.exception;

public class UnknownCompanyException extends ParserException{
	@java.io.Serial
	private static final long serialVersionUID = 202616112100L;
	private int _id;

	public UnknownCompanyException(int id){
		_id = id;
	}

	@Override
	public String getMessage() {
		return "Empresa com o id: "+ _id + " não existe no sistema.";
	}
}
