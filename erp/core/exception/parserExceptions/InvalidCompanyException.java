package erp.core.exception.parserExceptions;

public class InvalidCompanyException  extends ParserException{
    
    @Override
    public String getMessage() {
        return "Nome da empresa não pode ser vazio/nulo";
    }
}
