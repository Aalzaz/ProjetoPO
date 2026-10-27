package erp.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/**
 * Represents an error indicating that no product is registered with the supplied
 * identifier.
 */
public class UnknownProductKeyException extends CommandException {
  @java.io.Serial
  private static final long serialVersionUID = 202607021200L;

  public UnknownProductKeyException(int id) {
    super(Message.unknownProduct(id));
  }
}
