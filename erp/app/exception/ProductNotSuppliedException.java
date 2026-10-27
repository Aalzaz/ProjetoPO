package erp.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/** 
 * Represents an error indicating that the given company does not supply the given product.
 */
public class ProductNotSuppliedException extends CommandException {

  @java.io.Serial
  private static final long serialVersionUID = 202608261200L;

  public ProductNotSuppliedException(int idCompany, int idProduct) {
    super(Message.productNotSupplied(idCompany, idProduct));
  }
}
