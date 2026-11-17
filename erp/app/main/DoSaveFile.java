package erp.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import erp.core.ErpManager;
import erp.core.exception.MissingFileAssociationException;
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
    }

    @Override
    protected final void execute() {
		try {
			super._receiver.save();
		}
		catch (FileNotFoundException e) {
        	// TODO handle file not found
		}
		catch (MissingFileAssociationException e) {
			if (super._receiver.hasUnsavedChanges() == true) {
				addStringField("fileName", Prompt.newSaveAs());
				try {
					super._receiver.saveAs(stringField("fileName"));
				}
				catch (FileNotFoundException ex) {
					// TODO handle file not found
				}
				catch (MissingFileAssociationException ex) {
					//TODO
				}
				catch (IOException ex) {
					//TODO
				}
			}
		}
		catch (IOException e) {
        	// TODO handle stream exception
		}
    }
}
