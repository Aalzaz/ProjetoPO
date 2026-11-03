package erp.core.exception;

public class UnknownProductException extends Exception{
    @java.io.Serial
    private static final long serialVersionUID = 202610272000L;
    private final int _id;

    public UnknownProductException(int id) { 
        _id = id; 
    }
    public int id() { 
        return _id; 
    }
}
