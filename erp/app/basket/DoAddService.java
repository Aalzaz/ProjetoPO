package erp.app.basket;

import erp.core.ErpManager;
import erp.core.Basket;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/** 
 * Adds an extra service to the purchase. Services combine, and may repeat.
 */
class DoAddService extends BasketCommand {

  DoAddService(ErpManager manager, Basket basket) {
    super(Label.ADD_SERVICE, manager, basket);
    //FIXME maybe define fields
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
