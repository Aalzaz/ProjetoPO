package erp.app.basket;

import erp.core.ErpManager;
import erp.core.exception.ProductNotInBasketException;
import erp.core.exception.ProductNotSuppliedException;
import erp.app.exception.NotEnoughInventoryException;
import erp.core.Basket;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Changes the units of a product in the basket: a positive amount adds, a
 * negative one subtracts, and emptying a line removes the product altogether.
 * One command therefore covers the whole editing of a basket's contents.
 */
class DoChangeProduct extends BasketCommand {

	DoChangeProduct(ErpManager manager, Basket basket) {
		super(Label.CHANGE_PRODUCT, manager, basket);
		addIntegerField("id", Prompt.productToChange());
		addIntegerField("quant", Prompt.amountToChange());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			int reg = _receiver.update(integerField("id"), integerField("quant"));
			if (reg > 0) {
				_display.popup(Message.productChanged(integerField("id"), reg));
			}
			if (reg == -2) {
				_display.popup(Message.productRemoved(integerField("id")));
			}
		}
		catch (ProductNotInBasketException e) {
			_display.popup(Message.productNotInBasket(integerField("id")));
		}
		catch (ProductNotSuppliedException e) {}
		catch (NotEnoughInventoryException e) {
			_display.popup(Message.notEnoughInventory(integerField("id")));
		}
	}
}
