package erp.app.main;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
//FIXME maybe import classes

/**
 * Saves the current state of the application to a file using the serialization
 * mechanism. If no file is currently associated with the application, asks the user for
 * the name of the file to save to.
 */
class DoSaveFile extends Command<ErpManager> {

  DoSaveFile(ErpManager receiver) {
    super(Label.SAVE_FILE, receiver);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() {
    //FIXME implement command
  }
}
