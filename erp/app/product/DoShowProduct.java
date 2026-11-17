package erp.app.product;

import erp.app.exception.UnknownProductKeyException;
import erp.core.ErpManager;
import erp.core.exception.UnknownProductException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Show a specific product.
 */
class DoShowProduct extends Command<ErpManager> {

	DoShowProduct(ErpManager receiver) {
		super(Label.SHOW_PRODUCT, receiver);
		addIntegerField("productId", Prompt.productId());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			_display.popup(_receiver.getProduct(integerField("productId")));
		} catch (UnknownProductException e) {
			throw new UnknownProductKeyException(integerField("productId"));
		}
	}
}
