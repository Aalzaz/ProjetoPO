package erp.core.exception;

public class UnknownClientException extends Exception{
    @java.io.Serial
    private static final long serialVersionUID = 202610272000L;
    private final String _name;
    private final String _email;

    public UnknownClientException(String name, String email) { 
        _name   = name;
        _email  = email;
    }
    public String name() { 
        return _name;
    }
    public String email() { 
        return _email;
    }
}
