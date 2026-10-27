package erp.core.exception;

/**
 * Thrown when an attempt is made to save the application's persistent state to its associated file,
 * but no file has been associated with the application yet.
 */
public class MissingFileAssociationException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202607021200L;
}
