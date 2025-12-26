package DesignPattern.Stratigies;

public class PayPal implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Bank Account No or UPI ID");
    }
    
}
