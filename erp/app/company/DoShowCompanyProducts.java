package erp.app.company;

import erp.app.exception.UnknownCompanyKeyException;
import erp.core.ErpManager;
import erp.core.exception.UnknownCompanyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/** 
 * Shows what a company supplies: product, units, price factor and selling price.
 */
class DoShowCompanyProducts extends Command<ErpManager> {

	DoShowCompanyProducts(ErpManager receiver) {
		super(Label.SHOW_COMPANY_PRODUCTS, receiver);
		addIntegerField("id", Prompt.companyId());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			_display.addAll(_receiver.availableProducts(integerField("id")));
		} catch (UnknownCompanyException e) {
			throw new UnknownCompanyKeyException(integerField("id"));
		}
	}
}
