package erp.core;

import erp.core.exception.*;
import java.io.IOException;
import java.io.Serializable;
// FIXME maybe import classes

/**
 * The whole ERP system: holds all the state and the domain logic.
 */
class Erp implements Serializable {

  @java.io.Serial
  private static final long serialVersionUID = 202608261200L;

  //FIXME maybe define attributes
  //FIXME maybe implement constructor
  //FIXME maybe implement methods

  /**
   * Read text input file at the beginning of the program and populates the
   * the state of this application with the domain entities represented in the text file.
   * 
   * @param filename name of the text input file to process
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException
                       /* FIXME maybe other exceptions */ {
    //FIXME implement method
  }
}
