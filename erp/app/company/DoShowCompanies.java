package erp.app.company;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Show all companies (ordered by id).
 */
class DoShowCompanies extends Command<ErpManager> {

	DoShowCompanies(ErpManager receiver) {
		super(Label.SHOW_COMPANIES, receiver);
		//FIXME maybe define fields
	}

	@Override
	protected final void execute() throws CommandException {
		//TODO
		for (int i = 0; i < _receiver.getCompanyNextId()-1; i++) {
			_display.addLine(_receiver.getCompany(i));
		}
		_display.display();
	}
}
