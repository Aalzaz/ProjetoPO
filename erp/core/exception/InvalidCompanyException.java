package erp.core.exception;

public class InvalidCompanyException  extends ParserException{
	@java.io.Serial
	private static final long serialVersionUID = 202616112100L;
	
	@Override
	public String getMessage() {
		return "Nome da empresa não pode ser vazio/nulo";
	}
}
