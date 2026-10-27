package erp.app.purchase;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/** 
 * Shows a purchase followed by each of its orders in detail.
 */
class DoShowPurchase extends Command<ErpManager> {

  DoShowPurchase(ErpManager receiver) {
    super(Label.SHOW_PURCHASE, receiver);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
