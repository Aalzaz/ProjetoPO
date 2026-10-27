package erp.app.basket;

import erp.core.ErpManager;
import erp.core.Basket;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Chooses how the purchase will be split into orders.
 */
class DoSetSplitStrategy extends BasketCommand {

  DoSetSplitStrategy(ErpManager manager, Basket basket) {
    super(Label.SET_SPLIT_STRATEGY, manager, basket);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
