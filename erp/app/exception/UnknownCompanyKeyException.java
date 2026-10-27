package erp.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/**
 * Represents an error indicating that no company is registered with the supplied
 * identifier.
 */
public class UnknownCompanyKeyException extends CommandException {
  @java.io.Serial
  private static final long serialVersionUID = 202607021200L;

  public UnknownCompanyKeyException(int id) {
    super(Message.unknownCompany(id));
  }
}
