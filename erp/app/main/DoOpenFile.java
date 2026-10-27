package erp.app.main;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Replaces the current state of the application with the state previously saved to a
 * file. Asks for the file name.
 */
class DoOpenFile extends Command<ErpManager> {

   DoOpenFile(ErpManager receiver) {
     super(Label.OPEN_FILE, receiver);
     //FIXME maybe define fields
   }

   @Override
   protected final void execute() throws CommandException {
     //FIXME implement command
   }
}
