package erp.core.exception.parserExceptions;

public class InvalidClientException extends ParserException{
    private String _field;
    private String _correction;
    
    public InvalidClientException(String field, String correction){
        _field = field;
        _correction = correction;
    }

    @Override
    public String getMessage() {
        return "Campo: " + _field + " - " + _correction + ".";
    }
}

