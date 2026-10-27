package erp.app.purchase;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Show all purchases (ordered by id).
 */
class DoShowPurchases extends Command<ErpManager> {

  DoShowPurchases(ErpManager receiver) {
    super(Label.SHOW_PURCHASES, receiver);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() {
    //FIXME implement command
  }
}
