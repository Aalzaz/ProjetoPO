package erp.app.basket;

public interface Prompt {

  static String productToChange() {
    return "Introduza o número do produto a alterar: ";
  }

  static String amountToChange() {
    return "Introduza a quantidade a alterar: ";
  }

  static String splitStrategy() {
    return "Introduza a estratégia de divisão em encomendas: ";
  }

  static String modality() {
    return "Introduza a modalidade de transporte: ";
  }

  static String serviceToAdd() {
    return "Introduza o serviço adicional a acrescentar: ";
  }
}
