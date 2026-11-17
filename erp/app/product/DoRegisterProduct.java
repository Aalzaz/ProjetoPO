package erp.app.product;

import erp.app.exception.ProductRegistrationFailedException;
import erp.core.ErpManager;
import erp.core.exception.InvalidProductException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Registers a product in the system's global registry. The extra fields of the
 * imported type are only asked for once that type is chosen, so a new product
 * type only adds its own branch here.
 */
class DoRegisterProduct extends Command<ErpManager> {

	DoRegisterProduct(ErpManager receiver) {
		super(Label.REGISTER_PRODUCT, receiver);
	}

	@Override
	protected final void execute() throws CommandException {
		Form form = new Form();

		form.addOptionField("type", Prompt.productType(), _receiver.productTypes());
		form.addStringField("name", Prompt.productName());
		form.addIntegerField("price", Prompt.basePrice());
		form.addOptionField("category", Prompt.productCategory(), _receiver.productCategories());
		form.addIntegerField("delay", Prompt.baseDelay());
		
		form.parse();

		int id;
		try {
			if (_receiver.productNeedsOrigin(form.optionField("type"))) {
				Form extraForm = new Form();
				extraForm.addStringField("origin", Prompt.originCountry());
				extraForm.addRealField("tax", Prompt.customsTax());
				extraForm.parse();
				
				id = _receiver.registerImportedProduct(
					form.stringField("name"), 
					form.integerField("price"),
					form.optionField("category"), 
					form.integerField("delay"), 
					extraForm.stringField("origin"), 
					extraForm.realField("tax")
				);
			} else {
				id = _receiver.registerNationalProduct(
					form.stringField("name"), 
					form.integerField("price"),
					form.optionField("category"), 
					form.integerField("delay")
				);
			}
		} catch (InvalidProductException | IllegalArgumentException e) {
			throw new ProductRegistrationFailedException(stringField("name"));
		}
		
		_display.popup(Message.registrationSuccessful(id));
	}
}