package erp.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/** 
 * Represents an error indicating that the data provided for a new product does not describe a
 * valid product.
 */
public class ProductRegistrationFailedException extends CommandException {

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    public ProductRegistrationFailedException(String name) {
        super(Message.productRegistrationFailed(name));
    }

}
