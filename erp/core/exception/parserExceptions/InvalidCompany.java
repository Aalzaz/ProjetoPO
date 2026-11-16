package erp.core.exception.parserExceptions;

public class InvalidCompany  extends ParserException{
    
    @Override
    public String getMessage() {
        return "Nome da empresa não pode ser vazio/nulo";
    }
}
