package erp.app.main;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
/**
 * Advance the current date.
 */
class DoAdvanceDate extends Command<ErpManager> {

	DoAdvanceDate(ErpManager receiver) {
		super(Label.ADVANCE_DATE, receiver);		
		addIntegerField("days", Prompt.daysToAdvance());
	}

	@Override
	protected final void execute() throws CommandException {
		int days = integerField("days");
		if (days > 0) {
			_receiver.advanceDate(days);
		}
	}
}