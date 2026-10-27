package erp.app.product;

interface Message {

  static String registrationSuccessful(int idProduct) {
    return "Novo produto criado com o número " + idProduct + ".";
  }
}
