package erp.app.main;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Open Client menu.
 */
class DoOpenMenuClients extends Command<ErpManager> {

  DoOpenMenuClients(ErpManager receiver) {
    super(Label.OPEN_MENU_CLIENTS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    (new erp.app.client.Menu(_receiver)).open();
  }
}
