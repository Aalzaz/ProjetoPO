package erp.core.exception;

public class ProductNotSuppliedException extends Exception {
    @java.io.Serial
    private static final long serialVersionUID = 202610272000L;
    private final int _companyId;
    private final int _productId;

    public ProductNotSuppliedException(int companyId, int productId) {
        _companyId = companyId;
        _productId = productId;
    }
    public int getCompanyId() { 
        return _companyId; 
    }
    public int getProductId() { 
        return _productId; 
    }
}