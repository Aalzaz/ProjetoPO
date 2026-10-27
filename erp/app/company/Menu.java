package erp.app.company;

import erp.core.ErpManager;

/** Menu for the company management operations. */
public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(ErpManager receiver) {
    super(Label.TITLE,
          new DoRegisterCompany(receiver),
          new DoShowCompany(receiver),
          new DoShowCompanies(receiver),
          new DoChangeProductInventory(receiver),
          new DoShowCompanyProducts(receiver),
          new DoShowCompanyClients(receiver));
  }
}
