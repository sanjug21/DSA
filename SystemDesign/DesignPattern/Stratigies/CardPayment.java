package DesignPattern.Stratigies;

public class CardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Card Details - Card Number, Expiry Date, CVV");
        
    }
}
