package erp.app.exception;

interface Message {

  static String problemOpeningFile(Exception cause) {
    return "Problema ao abrir ficheiro: " + cause.getMessage();
  }
  
  static String unknownCompany(int id) {
    return "A empresa " + id + " não existe.";
  }

  static String unknownProduct(int id) {
    return "O produto " + id + " não existe.";
  }

  static String unknownClient(String id) {
    return "O cliente " + id + " não existe.";
  }

  static String unknownPurchase(int id) {
    return "A compra " + id + " não existe.";
  }

  static String notEnoughInventory(int idProduct) {
    return "O produto " + idProduct + " não tem exemplares suficientes.";
  }

  static String productNotSupplied(int idCompany, int idProduct) {
    return "A empresa " + idCompany + " não disponibiliza o produto " + idProduct + ".";
  }

  static String companyRegistrationFailed(String name) {
    return "Registo de empresa falhado: nome '" + name + "'.";
  }

  static String clientRegistrationFailed(String name, String email) {
    return "Registo de cliente falhado: nome '" + name + "', email '" + email + "'.";
  }

  static String productRegistrationFailed(String name) {
    return "Registo de produto falhado: nome '" + name + "'.";
  }
}
