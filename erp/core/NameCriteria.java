package erp.core;

public class NameCriteria extends SearchCriteria {
	@java.io.Serial
	private static final long serialVersionUID = 202608261200L;

	@Override
	public String myName() {
		return "NOME";
	}
	
	@Override
	public boolean matches(Product prd, String term) {
		return prd.name().equalsIgnoreCase(term);
	}
}
