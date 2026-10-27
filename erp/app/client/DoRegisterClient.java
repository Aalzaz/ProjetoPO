package erp.app.client;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/** 
 * Registers a client. Asks the name and email of the new client to the user. The e-mail
 * address is its identifier, so it must be new.
 */
class DoRegisterClient extends Command<ErpManager> {

  DoRegisterClient(ErpManager receiver) {
    super(Label.REGISTER_CLIENT, receiver);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
