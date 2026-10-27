package erp.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/**
 * Represents an error indicating that no client is registered with the supplied
 * identifier.
 */
public class UnknownClientKeyException extends CommandException {
  @java.io.Serial
  private static final long serialVersionUID = 202607021200L;

  public UnknownClientKeyException(String id) {
    super(Message.unknownClient(id));
  }
}
