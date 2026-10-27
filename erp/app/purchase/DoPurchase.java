package erp.app.purchase;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Starts (or resumes) a purchase: it asks whose basket, at which company, and
 * opens the basket menu over it.
 *
 * <p>The purchase itself is not made here — it is the basket menu's
 * {@code Finalizar compra} that turns the basket into a purchase. Since the
 * basket belongs to the client, leaving the menu without checking out keeps
 * everything for the next visit.</p>
 */
class DoPurchase extends Command<ErpManager> {

  DoPurchase(ErpManager receiver) {
    super(Label.DO_PURCHASE, receiver);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
