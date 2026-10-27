package erp.app.client;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybecore. import classes

/**
 * Show a specific client. Asks the id of the client to show, and then show it to the user.
 */
class DoShowClient extends Command<ErpManager> {

  DoShowClient(ErpManager receiver) {
    super(Label.SHOW_CLIENT, receiver);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
