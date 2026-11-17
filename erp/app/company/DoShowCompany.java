package erp.app.company;

import erp.app.exception.UnknownCompanyKeyException;
import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import erp.core.exception.UnknownCompanyException;

/**
 * Show a specific company.
 */
class DoShowCompany extends Command<ErpManager> {

	DoShowCompany(ErpManager receiver) {
		super(Label.SHOW_COMPANY, receiver);
		addIntegerField("id", Prompt.companyId());
	}

	@Override
	protected final void execute() throws CommandException {
		//FIXME maybe change company getter name
		try {
			_display.popup(super._receiver.getCompany(integerField("id")));
		}
		catch (UnknownCompanyException e) {
			throw new UnknownCompanyKeyException(integerField("id"));
		}
	}
}
