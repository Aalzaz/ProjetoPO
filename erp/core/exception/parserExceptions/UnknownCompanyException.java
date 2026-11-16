package erp.core.exception.parserExceptions;

public class UnknownCompanyException extends ParserException{
    private int _id;

    public UnknownCompanyException(int id){
        _id = id;
    }

    @Override
    public String getMessage() {
        return "Empresa com o id: "+ _id + " não existe no sistema.";
    }
}
