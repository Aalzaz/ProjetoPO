package erp.app.product;

import erp.core.ErpManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Searches the product registry by one of the criteria the core offers.
 */
class DoPerformSearch extends Command<ErpManager> {

	DoPerformSearch(ErpManager receiver) {
		super(Label.PERFORM_SEARCH, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		Form form = new Form();
		form.addOptionField("criterion", Prompt.searchCriterion(), _receiver.searchCriteria());
		form.addStringField("term", Prompt.searchTerm());
		form.parse();

		String criterion = form.optionField("criterion");
        String term = form.stringField("term");

		_display.addAll(_receiver.searchProducts(criterion, term));
	}
}
