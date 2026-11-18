package erp.app.client;

import erp.app.exception.ClientRegistrationFailedException;
import erp.core.ErpManager;
import erp.core.exception.InvalidClientException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/** 
 * Registers a client. Asks the name and email of the new client to the user. The e-mail
 * address is its identifier, so it must be new.
 */
class DoRegisterClient extends Command<ErpManager> {

	DoRegisterClient(ErpManager receiver) {
		super(Label.REGISTER_CLIENT, receiver);
		addStringField("name", Prompt.clientName());
		addStringField("email", Prompt.clientEMail());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			_receiver.registerClient(stringField("name"),stringField("email"));
			_display.popup(Message.registrationSuccessful(stringField("email")));
		} catch (InvalidClientException e) {
			throw new ClientRegistrationFailedException(stringField("name"),stringField("email"));
		}
	}
}
