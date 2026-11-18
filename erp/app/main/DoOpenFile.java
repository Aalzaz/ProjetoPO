package erp.app.main;

import java.io.IOException;
import erp.app.exception.FileOpenFailedException;
import erp.core.ErpManager;
import erp.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
/**
 * Replaces the current state of the application with the state previously saved to a
 * file. Asks for the file name.
 */
class DoOpenFile extends Command<ErpManager> {

	DoOpenFile(ErpManager receiver) {
		super(Label.OPEN_FILE, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
	try {
		if (_receiver.hasUnsavedChanges() && Form.confirm(Prompt.saveBeforeExit())) {
			try {
				_receiver.save();
			}
			catch (MissingFileAssociationException ex) {
				try {
					super._receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
				}
				catch (MissingFileAssociationException | IOException exc) {
					throw new FileOpenFailedException(exc);
				}
			}
			catch (IOException ex) {
				throw new FileOpenFailedException(ex);
			}
		}
		_receiver.load(Form.requestString(Prompt.openFile()));
	} 
	catch (IOException | ClassNotFoundException e) {
		throw new FileOpenFailedException(e);
	}
}
}
