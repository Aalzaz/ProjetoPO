package erp.core;

public class OriginCriteria extends SearchCriteria {
	@java.io.Serial
	private static final long serialVersionUID = 202608261200L;
	
	@Override
	public String myName() {
		return "ORIGEM";
	}
	
	@Override
	public boolean matches(Product prd, String term) {
		return prd.matchesOrigin(term);
	}
}
