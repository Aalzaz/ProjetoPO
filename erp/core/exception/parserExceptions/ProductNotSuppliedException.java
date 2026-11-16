package erp.core.exception.parserExceptions;

public class ProductNotSuppliedException extends ParserException {
    @java.io.Serial
    private static final long serialVersionUID = 202610272000L;
    private final int _companyId;
    private final int _productId;

    public ProductNotSuppliedException(int companyId, int productId) {
        _companyId = companyId;
        _productId = productId;
    }
    @Override
    public String getMessage() {
        return "Produto( id: " + _productId + ") não disponível pela empresa( id: " + _companyId + ")";
    }
    
}