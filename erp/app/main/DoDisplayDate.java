package erp.app.main;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Display the current date.
 */
class DoDisplayDate extends Command<ErpManager> {

  DoDisplayDate(ErpManager receiver) {
    super(Label.DISPLAY_DATE, receiver);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
