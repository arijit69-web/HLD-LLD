package Lec_1_OOPs;

public class Client {

    public static void main(String args[]) {

        PaymentService ps = new PaymentService();
        ps.addPaymentMethod("ArijitDebitCard",new DebitCard("1234", "Arijit Sarkar"));
        ps.addPaymentMethod("ArijitCreditCard",new CreditCard("5678", "Arijit Sarkar"));
        ps.addPaymentMethod("ArijitUPI",new UPI("9830"));
        ps.addPaymentMethod("ArijitWallet",new Wallet());

        ps.makePayment("ArijitUPI", 200);
        ps.makePayment("ArijitCreditCard", 500);
        ps.makePayment("ArijitWallet", 500);


    }

}
