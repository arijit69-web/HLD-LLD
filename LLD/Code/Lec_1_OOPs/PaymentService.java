package Lec_1_OOPs;

import java.util.HashMap;

public class PaymentService {

    // Why PaymentMethod? Because eventually what we need in this class is we want to use the pay method. We don't care how they are created, but we want to use them as payment methods
    HashMap<String,PaymentMethod> paymentMethods; // PaymentMethod is also Parent of all the classes

    PaymentService()
    {
        paymentMethods = new HashMap<>();
    }
    public void addPaymentMethod(String name, PaymentMethod paymentMethod)
    {
        paymentMethods.put(name,paymentMethod);

    }
    public  void makePayment(String name, int amount)
    {

        PaymentMethod pm = paymentMethods.get(name);// Loosely Coupled
        pm.pay(amount); // Run-time polymorphism or Dynamic binding allows different classes to provide different implementations of the same method. Which implementation gets executed is decided at runtime, based on the actual object being referenced.

    }



}
