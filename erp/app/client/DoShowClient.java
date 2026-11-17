package erp.app.client;

import erp.app.exception.UnknownClientKeyException;
import erp.core.ErpManager;
import erp.core.exception.UnknownClientException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Show a specific client. Asks the id of the client to show, and then show it to the user.
 */
class DoShowClient extends Command<ErpManager> {

  DoShowClient(ErpManager receiver) {
    super(Label.SHOW_CLIENT, receiver);
    addStringField("email", Prompt.clientId());
  }

  @Override
  protected final void execute() throws CommandException, UnknownClientKeyException {
    try {
		_display.popup(_receiver.getClient(stringField("email")));
	}catch(UnknownClientException e){
		throw new UnknownClientKeyException(stringField("email"));
	}
  }
}
