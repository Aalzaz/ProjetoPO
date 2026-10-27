package erp.core;

import erp.core.exception.*;
import java.io.*;
//FIXME maybe import classes

/**
 * The façade of the ERP core: the single entry point through which the
 * application interacts with the domain.
 *
 * <p><b>Façade</b> pattern.</p>
 */
public class ErpManager {

  /** The object doing all the actual work. */
  private Erp _erp = new Erp();

  //FIXME maybe define constructors

  // --- persistence -------------------------------------------------------

  /**
   * Saves the serialized application's state into the file associated to the current Erp
   * application
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current library does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws MissingFileAssociationException, IOException {
    //FIXME implement method
  }

  /**
   * Saves the serialized application's state into the specified file. The current Erp
   * application is associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current library does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws MissingFileAssociationException, IOException {
    //FIXME implement method
  }

  /**
   * Loads the previously serialized application's state as set it as the current Erp
   * application.
   *
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    //FIXME implement method
  }

  /**
   * Read text input file and initializes the current application (which should be empty)
   * with the domain entities representeed in the import file.
   *
   * @param datafile name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
      if (filename != null && !filename.isEmpty())
        _erp.importFile(filename);
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(filename, e);
    }
  }
  
  //FIXME implement other methods
}
