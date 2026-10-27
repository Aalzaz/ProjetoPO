package erp.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/**
 * Represents an error indicating that there was an error while
 * processing a file with a serialized application.
 */
public class FileOpenFailedException extends CommandException {
  @java.io.Serial
  private static final long serialVersionUID = 202607021200L;

  public FileOpenFailedException(Exception e) {
    super(Message.problemOpeningFile(e), e);
  }
}
