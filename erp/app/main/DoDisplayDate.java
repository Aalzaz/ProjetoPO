package erp.app.main;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Display the current date.
 */
class DoDisplayDate extends Command<ErpManager> {

	DoDisplayDate(ErpManager receiver) {
		super(Label.DISPLAY_DATE, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		int date = _receiver.getDate();
		_display.popup(Message.currentDate(date));
	}
}
