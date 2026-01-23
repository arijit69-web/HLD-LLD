package Lec_2_SOLID.SRP.GoodCode;

public class EmailService {

    // Sends the invoice email to the user

    public void sendEmailNotification(Invoice invoice){
        System.out.println("Sending email notification for invoice: "+invoice);
    }
}
