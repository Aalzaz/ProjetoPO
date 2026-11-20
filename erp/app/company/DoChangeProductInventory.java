package erp.app.company;
import erp.app.exception.UnknownCompanyKeyException;
import erp.app.exception.UnknownProductKeyException;
import erp.core.ErpManager;
import erp.core.exception.InvalidStockOperationException;
import erp.core.exception.UnknownCompanyException;
import erp.core.exception.UnknownProductException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Changes what a company holds of a product — the amount is added to its
 * inventory and the factor replaces the previous one — starting the supply if
 * the company did not offer the product yet.
 *
 * <p>Reaching zero units only makes the product unavailable at that company: the
 * product itself stays in the system.</p>
 */
class DoChangeProductInventory extends Command<ErpManager> {

	DoChangeProductInventory(ErpManager receiver) {
		super(Label.CHANGE_PRODUCT_INVENTORY, receiver);
		addIntegerField("companyId", Prompt.companyId());
		addIntegerField("productId", erp.app.product.Prompt.productId());
		addIntegerField("delta", Prompt.amountToUpdate());
		addRealField("factor", Prompt.priceFactor());
	}

	@Override
    protected final void execute() throws CommandException {
        try{
            _receiver.updateStock(integerField("companyId"), integerField("productId"), 
                                            integerField("delta"), realField("factor"));
            _display.popup(Message.supplySuccessful(integerField("companyId"), integerField("productId")));

        }catch(UnknownCompanyException e){
            throw new UnknownCompanyKeyException(integerField("companyId"));
        }
        catch (UnknownProductException e) {
            throw new UnknownProductKeyException(integerField("productId"));
        }
        catch (InvalidStockOperationException e) {
            _display.popup(Message.cannotChangeInventory((integerField("productId"))));
        }
    }

}