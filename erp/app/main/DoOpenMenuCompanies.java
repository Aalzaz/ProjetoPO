package erp.app.main;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Open Company menu.
 */
class DoOpenMenuCompanies extends Command<ErpManager> {

  DoOpenMenuCompanies(ErpManager receiver) {
    super(Label.OPEN_MENU_COMPANIES, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    (new erp.app.company.Menu(_receiver)).open();
  }
}
