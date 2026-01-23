package Lec_2_SOLID.OCP.BadCode;

public class PaymentProcessor {
    public void processPayment(String paymentMethod,double amount){
        /*
       Problem: I want to add a new payment method (UPI), but this requires modifying existing functions that are already written and tested.
        This violates the Open/Closed Principle, which states that classes should be open for extension but closed for modification.
         */
        if(paymentMethod.equals("CreditCard")){
            //business logic
            System.out.println("Making payment via Credit Card :" + amount);
        }
        else if(paymentMethod.equals("Debit Card")){
            //business logic
            System.out.println("Making payment via Debit Card :" + amount);
        }
        else if(paymentMethod.equals("Paypal")){
            //business logic
            System.out.println("Making payment via PayPal :" + amount);
        }
        else{
            throw new IllegalArgumentException("Unsupported payment method " +paymentMethod);
        }
    }
}