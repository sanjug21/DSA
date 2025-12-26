package DesignPattern.Stratigies;

public class ShoppingCartClient {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        
        
        cart.PaymentStrategy(new CardPayment());
        cart.checkout(1000);
        cart.PaymentStrategy(new PayPal());
        cart.checkout(2000);
        
    }
}
