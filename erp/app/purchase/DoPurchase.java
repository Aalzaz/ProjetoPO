package erp.app.purchase;

import erp.app.exception.UnknownClientKeyException;
import erp.app.exception.UnknownCompanyKeyException;
import erp.core.Basket;
import erp.core.ErpManager;
import erp.core.exception.UnknownClientException;
import erp.core.exception.UnknownCompanyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Starts (or resumes) a purchase: it asks whose basket, at which company, and
 * opens the basket menu over it.
 *
 * <p>The purchase itself is not made here — it is the basket menu's
 * {@code Finalizar compra} that turns the basket into a purchase. Since the
 * basket belongs to the client, leaving the menu without checking out keeps
 * everything for the next visit.</p>
 */
class DoPurchase extends Command<ErpManager> {

	DoPurchase(ErpManager receiver) {
		super(Label.DO_PURCHASE, receiver);
		addStringField("email", erp.app.client.Prompt.clientId());	
		addIntegerField("companyId", erp.app.company.Prompt.companyId());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			Basket bas = _receiver.getBasket(stringField("email"), integerField("companyId"));
			new erp.app.basket.Menu(_receiver, bas).open();
		} catch (UnknownClientException e) {
			throw new UnknownClientKeyException(stringField("email"));
		} catch (UnknownCompanyException e){
			throw new UnknownCompanyKeyException(integerField("companyId"));
		}
	}
}
