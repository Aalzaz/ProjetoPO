package erp.app.company;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * 4.2. Show the clients that have interacted with a company (ordered by id).
 */
class DoShowCompanyClients extends Command<ErpManager> {

  DoShowCompanyClients(ErpManager receiver) {
    super(Label.SHOW_COMPANY_CLIENTS, receiver);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
