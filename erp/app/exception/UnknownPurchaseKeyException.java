package erp.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/**
 * Represents an error indicating that no purchase is registered with the supplied
 * identifier.
 */
public class UnknownPurchaseKeyException extends CommandException {
  @java.io.Serial
  private static final long serialVersionUID = 202607021200L;

  public UnknownPurchaseKeyException(int id) {
    super(Message.unknownPurchase(id));
  }
}
