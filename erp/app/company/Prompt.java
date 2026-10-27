package erp.app.company;

public interface Prompt {

  static String companyId() {
    return "Introduza o número da empresa: ";
  }

  static String companyName() {
    return "Introduza o nome da empresa: ";
  }

  static String amountToUpdate() {
    return "Introduza a quantidade a actualizar: ";
  }

  static String priceFactor() {
    return "Introduza o factor multiplicativo do preço recomendado: ";
  }
}
