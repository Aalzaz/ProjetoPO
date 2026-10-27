package erp.app.basket;

import erp.core.ErpManager;
import erp.core.Basket;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * Chooses the modality every order of this purchase will travel under, which
 * fixes both what the transport costs and how long it takes.
 *
 * <p>Only the modalities with a tariff defined are offered, so the choice always
 * resolves to a usable rate.</p>
 */
class DoSetModality extends BasketCommand {

  DoSetModality(ErpManager manager, Basket basket) {
    super(Label.SET_MODALITY, manager, basket, x -> true /* FIXME correct validity condition */);
    //FIXME maybe define fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
  }
}
