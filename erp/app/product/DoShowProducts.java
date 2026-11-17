package erp.app.product;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all products (ordered by id).
 */
class DoShowProducts extends Command<ErpManager> {

	DoShowProducts(ErpManager receiver) {
		super(Label.SHOW_PRODUCTS, receiver);
	}

	@Override
	protected final void execute() {
		_display.addAll(_receiver.getProducts());
	}
}
