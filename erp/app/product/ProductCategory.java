package erp.app.product;
public enum ProductCategory{
    ALIMENTAR(0),
    ELETRONICA(1),
    VESTUARIO(2);
    private final int _id;

    //Constructor----------------------------------------------------------------------------------
   ProductCategory(int id) {
        _id = id;
    }
    public int id(){
        return _id;
    }
    //----------------------------------------------------------------------------------------------
    /**
     * 
     * @param obj
     * @return True if the object is a instance of ProductCategory and has the same id. Else return False
     */
    public boolean sameId(Object obj){
        if(obj instanceof  ProductCategory category){
            return category._id == _id;
        }
        return false;


    }

}