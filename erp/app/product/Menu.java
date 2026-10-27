package erp.app.product;

import erp.core.ErpManager;

/**  
 * Menu for the product management operations.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(ErpManager receiver) {
    super(Label.TITLE,
          new DoRegisterProduct(receiver),
          new DoShowProduct(receiver),
          new DoShowProducts(receiver),
          new DoPerformSearch(receiver));
  }
}
