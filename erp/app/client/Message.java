package erp.app.client;

interface Message {

  static String registrationSuccessful(String idClient) {
    return "Novo cliente criado: " + idClient + ".";
  }
}
