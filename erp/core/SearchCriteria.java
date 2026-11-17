package erp.core;

import java.io.Serializable;

public abstract class SearchCriteria implements Serializable{
	@java.io.Serial
	private static final long serialVersionUID = 202608261200L;
	
	private final String _name = myName();

	abstract public String myName();
	abstract public boolean matches(Product prd, String term);
	public String name(){ return _name; }
}
