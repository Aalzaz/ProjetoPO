package erp.app.product;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Searches the product registry by one of the criteria the core offers.
 */
class DoPerformSearch extends Command<ErpManager> {

  DoPerformSearch(ErpManager receiver) {
    super(Label.PERFORM_SEARCH, receiver);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
