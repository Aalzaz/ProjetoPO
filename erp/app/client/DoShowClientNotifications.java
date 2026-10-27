package erp.app.client;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Shows a client's pending notifications. Asks for the client's identifier and then
 * presents the notifications received by the client through the default notification
 * mechanism.
 */
class DoShowClientNotifications extends Command<ErpManager> {

  DoShowClientNotifications(ErpManager receiver) {
    super(Label.SHOW_CLIENT_NOTIFICATIONS, receiver);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
