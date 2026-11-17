package erp.core;

public class CategoryCriteria extends SearchCriteria{
	@java.io.Serial
	private static final long serialVersionUID = 202608261200L; 

	@Override
	public String myName() {
		return "CATEGORIA";
	}
	@Override
	public boolean matches(Product prd, String term) {
		try{
			return prd.category() == ProductCategory.valueOf(term.toUpperCase());
		}catch(IllegalArgumentException e){
			return false;
		}
	}
	
}