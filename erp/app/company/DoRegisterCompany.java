package erp.app.company;

import erp.app.exception.CompanyRegistrationFailedException;
import erp.core.ErpManager;
import erp.core.exception.parserExceptions.StringException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Register a new company.
 */
class DoRegisterCompany extends Command<ErpManager> {

  DoRegisterCompany(ErpManager receiver) {
    super(Label.REGISTER_COMPANY, receiver);
    addStringField("companyName", Prompt.companyName());
  }

  @Override
  protected final void execute() throws CommandException {
    String companyName = stringField("companyName");
    try {
      super._receiver.registerCompany(companyName);
    } 
    catch (StringException e) {
      throw new CompanyRegistrationFailedException(companyName);
    }
    finally {
      int companyId = super._reciever.
      _display.popup(Message.registrationSuccessful(0));
    }
  }
}
