package erp.core.exception.parserExceptions;

public class UnknownProductException extends ParserException{
    @java.io.Serial
    private static final long serialVersionUID = 202610272000L;
    private final int _id;

    public UnknownProductException(int id) { 
        _id = id; 
    }
    @Override
    public String getMessage() {
        return "Produto com o id: "+ _id + " não existe no sistema.";
    }
}
