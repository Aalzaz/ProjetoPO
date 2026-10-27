package erp.app.basket;

import erp.core.ErpManager;
import erp.core.Basket;

/**
 * The menu of a client's basket at one company: the whole purchase process, one
 * small command at a time, opened over the basket it acts on.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(ErpManager manager, Basket basket) {
    super(Label.TITLE,
          new DoChangeProduct(manager, basket),
          new DoShowBasket(manager, basket),
          new DoSetSplitStrategy(manager, basket),
          new DoSetModality(manager, basket),
          new DoAddService(manager, basket),
          new DoCheckout(manager, basket));
    }
}
