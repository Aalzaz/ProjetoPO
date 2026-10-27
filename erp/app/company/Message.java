package erp.app.company;

interface Message {

  static String registrationSuccessful(int idCompany) {
    return "Nova empresa criada com o número " + idCompany + ".";
  }

  static String supplySuccessful(int idCompany, int idProduct) {
    return "A empresa " + idCompany + " passou a disponibilizar o produto " + idProduct + ".";
  }

  static String cannotChangeInventory(int idProduct) {
    return "Não é possível alterar o inventário do produto " + idProduct + ".";
  }
}
