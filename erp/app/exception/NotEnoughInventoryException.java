package erp.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/** 
 * Represents an error indicating that a basket could not be checked out because a product
 * ran out of stock.
 */
public class NotEnoughInventoryException extends CommandException {

  @java.io.Serial
  private static final long serialVersionUID = 202608261200L;

  public NotEnoughInventoryException(int idProduct) {
    super(Message.notEnoughInventory(idProduct));
  }
}
