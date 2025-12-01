package Lec_1_OOPs;

public class CreditCard extends Card {


    public CreditCard(String cardNo, String userName) {
        super(cardNo, userName);
    }

    @Override
    public void pay(int amount) {
        //Since the method is abstract, it must be implemented by any child class that extends this class.
        System.out.println("Making Payment using Credit Card! of amount: " + amount);

    }
}
