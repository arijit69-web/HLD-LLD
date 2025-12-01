package Lec_1_OOPs;

public class DebitCard extends Card {

    public DebitCard(String cardNo, String userName) {
        super(cardNo, userName);
    }

    @Override
    public void pay(int amount) {
        //Since the method is abstract, it must be implemented by any child class that extends this class.
        System.out.println("Making Payment using Debit Card! of amount: "+ amount);

    }
}
