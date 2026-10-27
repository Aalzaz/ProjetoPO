package erp.app.client;

import erp.core.ErpManager;

/**
 * Menu for the client management operations.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {
  public Menu(ErpManager receiver) {
    super(Label.TITLE,
          new DoRegisterClient(receiver),
          new DoShowClient(receiver),
          new DoShowClients(receiver),
          new DoShowClientNotifications(receiver));
    }
}
