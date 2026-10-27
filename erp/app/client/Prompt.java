package erp.app.client;

public interface Prompt {

  /** A client is identified by its e-mail address. */
  static String clientId() {
    return "Introduza o endereço de correio do cliente: ";
  }
  
  static String clientName() {
    return "Introduza o nome do cliente: ";
  }
  
  static String clientEMail() {
    return "Introduza o endereço de correio do cliente: ";
  }
}
