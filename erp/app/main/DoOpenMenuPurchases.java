package erp.app.main;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Open Purchases menu.
 */
class DoOpenMenuPurchases extends Command<ErpManager> {

  DoOpenMenuPurchases(ErpManager receiver) {
    super(Label.OPEN_MENU_PURCHASES, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    (new erp.app.purchase.Menu(_receiver)).open();
  }
}
