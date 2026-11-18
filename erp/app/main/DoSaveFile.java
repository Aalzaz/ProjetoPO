package erp.app.main;

import java.io.IOException;
import erp.app.exception.FileOpenFailedException;
import erp.core.ErpManager;
import erp.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
/**
 * Saves the current state of the application to a file using the serialization
 * mechanism. If no file is currently associated with the application, asks the user for
 * the name of the file to save to.
 */
class DoSaveFile extends Command<ErpManager> {

    DoSaveFile(ErpManager receiver) {
    super(Label.SAVE_FILE, receiver);
    }

    @Override
    protected final void execute() throws CommandException{
		if (super._receiver.hasUnsavedChanges() == true) {
			try {
				super._receiver.save();
			}
			catch (MissingFileAssociationException e) {
				try {
					_receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
				}
				catch (MissingFileAssociationException | IOException ex) {
					throw new FileOpenFailedException(ex);
				}
			}
			catch (IOException e) {
				throw new FileOpenFailedException(e);
			}
		}
    }
}
