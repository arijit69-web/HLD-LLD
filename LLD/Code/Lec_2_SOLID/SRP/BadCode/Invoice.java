package Lec_2_SOLID.SRP.BadCode;

public class Invoice {
    private double amount;

    public Invoice(double amount){
        this.amount = amount;
    }
    /*
     Bad Code: As we have multiple responsibilities being assigned to the invoice class, violates our SRP
     */
    public void generateInvoice(){
        System.out.println("Invoice generated & printed for amount " +amount);
    }

    /*
    This functionality should be moved to a separate class. If we ever change the way data is saved to the database or how logging is handled before insertion, we shouldn’t have to modify the Invoice class.
     */
    public void saveToDatabase(){
        System.out.println("Saving invoice to Database ");
    }
    /*
   This functionality should be placed in a separate class. If we ever change our email-sending service or the way emails are delivered, we shouldn’t have to modify the Invoice class.
    */
    public void sendEmailNotification(){
        System.out.println("Sending email notification for invoice ");
    }
}