package Lec_1_OOPs;

public class UPI  implements  PaymentMethod{
    private String upiId;

    UPI(String upiId)
    {
        this.upiId = upiId;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Making Payment using UPI! of amount: " + amount);

    }
}
