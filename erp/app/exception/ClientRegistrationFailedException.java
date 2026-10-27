package erp.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/** 
 * Represents an error indicating that the data provided for a new client is invalid.
 */
public class ClientRegistrationFailedException extends CommandException {
  @java.io.Serial
  private static final long serialVersionUID = 202607021200L;

  public ClientRegistrationFailedException(String name, String email) {
    super(Message.clientRegistrationFailed(name, email));
  }
}
