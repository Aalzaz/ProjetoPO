package erp.core.exception.parserExceptions;

public class IntegerException extends ParserException {
    private String  _errorField;
    private boolean _includeZero;

    public IntegerException(String field, boolean includeZero){
        _errorField = field;
        _includeZero = includeZero;
    }
    public String getMessage(){ 
        if(!_includeZero)    
            return "Número no campo: "+ _errorField +" deve ser um inteiro positivo.";
        else
            return "Número no campo: "+ _errorField +" deve ser um inteiro maior ou igual a zero.";
        }
}           
