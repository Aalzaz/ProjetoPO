package erp.app.basket;

interface Message {

  static String productChanged(int idProduct, int amount) {
    return "O cabaz tem agora " + amount + " x produto " + idProduct + ".";
  }
  
  static String productRemoved(int idProduct) {
    return "O produto " + idProduct + " foi removido do cabaz.";
  }
  
  static String productNotInBasket(int idProduct) {
    return "O produto " + idProduct + " não está no cabaz.";
  }

  static String notEnoughInventory(int idProduct) {
    return "O produto " + idProduct + " não tem exemplares suficientes.";
  }
}
