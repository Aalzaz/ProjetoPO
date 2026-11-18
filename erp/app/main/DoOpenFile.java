package erp.app.main;

import java.io.IOException;
import pt.tecnico.uilib.forms.Form;

import erp.app.exception.FileOpenFailedException;
import erp.core.ErpManager;
import erp.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Replaces the current state of the application with the state previously saved to a
 * file. Asks for the file name.
 */
class DoOpenFile extends Command<ErpManager> {

	DoOpenFile(ErpManager receiver) {
		super(Label.OPEN_FILE, receiver);
		addStringField("filename", Prompt.openFile());
	}

	@Override
	protected final void execute() throws CommandException {
	try {
		if (_receiver.hasUnsavedChanges() && Form.confirm(Prompt.saveBeforeExit())) {
			// reutilizar a mesma lógica do DoSaveFile (pedir nome se não houver associação)
		}
		_receiver.load(stringField("filename"));
	} catch (IOException | ClassNotFoundException e) {
		throw new FileOpenFailedException(e);
	}
}
}
