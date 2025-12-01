package Lec_1_OOPs;

public class Wallet  implements  PaymentMethod{
/* What is good about this loosely coupled design?

   Since we added the Wallet class, we didn’t need to modify the PaymentService class at all.
   Without this design, we would have ended up hard-coding conditions, using type checks, etc.
   With loose coupling, you can simply create a new Wallet class and plug it in without changing the existing code.
*/


    @Override
    public void pay(int amount) {
        System.out.println("Making Payment using Wallet! of amount: "+ amount);

    }
}
