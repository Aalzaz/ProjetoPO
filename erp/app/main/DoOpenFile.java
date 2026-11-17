package erp.app.main;

import java.io.IOException;

import erp.app.exception.FileOpenFailedException;
import erp.core.ErpManager;
import erp.core.exception.UnavailableFileException;
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
		String filename = stringField("filename");
		try {
			super._receiver.load(filename); 
		}
    	//catch (UnavailableFileException e) {
    	//	throw new FileOpenFailedException(e);
    	//}
		catch (IOException e) {
			//TODO
		}
		catch (ClassNotFoundException e) {
			// TODO
		}
	}
}
