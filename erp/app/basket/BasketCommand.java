package erp.app.basket;

import erp.core.ErpManager;
import erp.core.Basket;
import pt.tecnico.uilib.menus.Command;

import java.util.function.Predicate;

/**
 * Base of the commands of the basket menu: they act <em>on a basket</em> (the
 * receiver), but still reach the domain through the façade.
 */
abstract class BasketCommand extends Command<Basket> {

  protected final ErpManager _manager;

  BasketCommand(String title, ErpManager manager, Basket basket) {
    super(title, basket);
    _manager = manager;
  }
  
  BasketCommand(String title, ErpManager manager, Basket basket, Predicate<Basket> valid) {
    super(title, basket, valid);
    _manager = manager;
  }

  BasketCommand(boolean last, String title, ErpManager manager, Basket basket, Predicate<Basket> valid) {
    super(last, title, basket, valid);
    _manager = manager;
  }
  
  /** The option array for a form field, from a list of keys given by the façade. */
  protected static String[] options(java.util.List<String> keys) {
    return keys.toArray(new String[0]);
  }
}
