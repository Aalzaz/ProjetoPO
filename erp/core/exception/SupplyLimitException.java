package erp.core.exception;

public class SupplyLimitException extends Exception {
	@java.io.Serial 
	private static final long serialVersionUID = 202611201535L;
	private int _companyId;
	private int _productId;
	
	public SupplyLimitException(int companyId, int productId){
		_companyId = companyId;
		_productId = productId;
	}
	
	public int companyId(){ return _companyId; }

	public int productId(){ return  _productId; }
}
