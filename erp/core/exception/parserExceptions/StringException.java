package erp.core.exception.parserExceptions;

public class StringException extends ParserException {
    private String  _errorField;

    public StringException(String field){
        _errorField = field;
    } 
    public String getMessage(){ return "Campo inválido(cadeias de caracteres vazia): " + _errorField; }
}
