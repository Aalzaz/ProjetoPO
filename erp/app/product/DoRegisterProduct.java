package erp.app.product;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Registers a product in the system's global registry. The extra fields of the
 * imported type are only asked for once that type is chosen, so a new product
 * type only adds its own branch here.
 */
class DoRegisterProduct extends Command<ErpManager> {

  DoRegisterProduct(ErpManager receiver) {
    super(Label.REGISTER_PRODUCT, receiver);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
