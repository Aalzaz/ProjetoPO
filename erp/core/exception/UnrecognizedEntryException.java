package erp.core.exception;

/**
 * Represents an error indicating that a given entry does not follow the textual
 * format associated with the type of the domain entity it describes.
 */
public class UnrecognizedEntryException extends Exception {

  @java.io.Serial
  private static final long serialVersionUID = 202607021200L;

  private final String _entrySpecification;
  
  public UnrecognizedEntryException(String entrySpecification) {
    _entrySpecification = entrySpecification;
  }
  
  public UnrecognizedEntryException(String entrySpecification, Exception cause) {
    super(cause);
    _entrySpecification = entrySpecification;
  }
  
  public String getEntrySpecification() {
    return _entrySpecification;
  }
}
