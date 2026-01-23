package Lec_2_SOLID.OCP.GoodCode;

public class Main {
    public static void main(String[] args) {
        /*
        Step 1: Create a Payment interface
        Step 2: Create separate classes for each payment method
        Step 3: PaymentProcessor should depend on the abstraction (Payment), not strings
         */
        PaymentProcessor processor = new PaymentProcessor();
        PaymentMethod creditCard = new CreditCard();
        /*
           Now, if you want to add UPI payment, you NEVER touch the existing code.
           No modification needed in PaymentProcessor
           System is open for extension but closed for modification
        */
        PaymentMethod upi = new UPI();

        processor.processPayment(creditCard,100);
        processor.processPayment(upi,120);
    }
}