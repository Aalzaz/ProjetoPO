package erp.app.basket;

import erp.core.ErpManager;
import erp.core.Basket;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Changes the units of a product in the basket: a positive amount adds, a
 * negative one subtracts, and emptying a line removes the product altogether.
 * One command therefore covers the whole editing of a basket's contents.
 */
class DoChangeProduct extends BasketCommand {

  DoChangeProduct(ErpManager manager, Basket basket) {
    super(Label.CHANGE_PRODUCT, manager, basket);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
