package erp.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/** 
 * Represents an error indicating that the data provided for the registration of a new
 * company is invalid.
 */
public class CompanyRegistrationFailedException extends CommandException {
  @java.io.Serial
  private static final long serialVersionUID = 202607021200L;

  public CompanyRegistrationFailedException(String name) {
    super(Message.companyRegistrationFailed(name));
  }
}
