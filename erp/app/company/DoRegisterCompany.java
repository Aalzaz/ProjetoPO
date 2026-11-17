package erp.app.company;

import erp.app.exception.CompanyRegistrationFailedException;
import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import erp.core.exception.InvalidCompanyException;

/**
 * Register a new company.
 */
class DoRegisterCompany extends Command<ErpManager> {

	DoRegisterCompany(ErpManager receiver) {
		super(Label.REGISTER_COMPANY, receiver);
		addStringField("companyName", Prompt.companyName());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			int id = super._receiver.registerCompany(stringField("companyName"));
		}
		catch (InvalidCompanyException e) {
			throw new CompanyRegistrationFailedException(stringField("companyName"));
		}
		finally {
			_display.popup(Message.registrationSuccessful(id));
		}
	}
}
