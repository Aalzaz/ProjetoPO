package erp.core;

public enum ProductType {
	NACIONAL(false), 
	IMPORTADO(true);
	private final boolean _needsOrigin;

	ProductType(boolean needsOrigin){
			_needsOrigin = needsOrigin;
	}
	public boolean productNeedsOrigin(){
		return _needsOrigin;
	}
}
