package DesignPattern.Stratigies;

public class ShoppingCart {
    private PaymentStrategy p;

   public void PaymentStrategy(PaymentStrategy paymentStrategy) {
        this.p = paymentStrategy;
    }

    public void checkout(int amount) {
        if(p==null) {
            throw new UnsupportedOperationException("Payment method not set");
        }
        p.pay(amount);
    }
}
