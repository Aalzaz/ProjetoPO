package erp.app.main;

import erp.core.ErpManager;

public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(ErpManager receiver) {
    super(Label.TITLE,
          new DoOpenFile(receiver),
          new DoSaveFile(receiver),
          new DoDisplayDate(receiver),
          new DoAdvanceDate(receiver),
          new DoOpenMenuCompanies(receiver),
          new DoOpenMenuProducts(receiver),
          new DoOpenMenuClients(receiver),
          new DoOpenMenuPurchases(receiver)
          );
  }
}
