package Lec_2_SOLID.OCP.GoodCode;

public class PaymentProcessor {
    /*
    When using strings, every new payment type needs:
    A new if/else
    New business logic inside PaymentProcessor
    This means modifying tested code repeatedly → high risk.
    But with abstraction, you just create a new class.
    Zero modification to existing logic.
     */
    public void processPayment(PaymentMethod paymentMethod,double amount){
        paymentMethod.pay(amount);// Runtime Polymorphism
    }
}
