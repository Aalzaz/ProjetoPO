package erp.app.product;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Registers a product in the system's global registry. The extra fields of the
 * imported type are only asked for once that type is chosen, so a new product
 * type only adds its own branch here.
 */
class DoRegisterProduct extends Command<ErpManager> {

	DoRegisterProduct(ErpManager receiver) {
		super(Label.REGISTER_PRODUCT, receiver);
		addStringField("type", Prompt.productType());
		addStringField("name", Prompt.productName());
		addIntegerField("price", Prompt.basePrice());
		addStringField("category", Prompt.productCategory());
		addIntegerField("delay", Prompt.baseDelay());
		addOptionField("origin", Prompt.originCountry(), Prompt.productType());
		addOptionField("tax", Prompt.customsTax(), Prompt.productType());
	}

	@Override
	protected final void execute() throws CommandException {
		if(stringField("type") == "NACIONAL"){
			_receiver.registerNationalProduct(stringField("name"), integerField("price"), 
			stringField("category"), integerField("delay"));
		}

	}
}
