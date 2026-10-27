package erp.core.exception;

/** 
 * Represents an error that occurred while processing the file containing a previously serialized
 * state of the application, either because the file does not exist or because deserialization failed.
 **/
public class UnavailableFileException extends Exception {

  @java.io.Serial
  private static final long serialVersionUID = 202607021200L;
  
  private final String _filename;
  
  public UnavailableFileException(String filename) {
    super("Erro a processar ficheiro " + filename);
    _filename = filename;
  }
  
  public String getFilename() {
    return _filename;
  }
}
