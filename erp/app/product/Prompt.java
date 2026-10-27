package erp.app.product;

public interface Prompt {

  static String productId() {
    return "Introduza o número do produto: ";
  }

  static String productType() {
    return "Introduza o tipo de produto: ";
  }

  static String productName() {
    return "Introduza o nome do produto: ";
  }

  static String basePrice() {
    return "Introduza o preço base: ";
  }

  static String productCategory() {
    return "Introduza a categoria: ";
  }

  static String baseDelay() {
    return "Introduza o prazo de entrega base: ";
  }

  static String originCountry() {
    return "Introduza o país de origem: ";
  }

  static String customsTax() {
    return "Introduza a taxa alfandegária: ";
  }

  static String searchCriterion() {
    return "Introduza o critério de pesquisa: ";
  }

  static String searchTerm() {
    return "Introduza o termo de pesquisa: ";
  }
}
