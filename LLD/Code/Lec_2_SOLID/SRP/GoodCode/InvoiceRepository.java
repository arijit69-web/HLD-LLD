package Lec_2_SOLID.SRP.GoodCode;

public class InvoiceRepository {

    // Saves the Invoice in the DB

    public void saveToDatabase(Invoice invoice){
        System.out.println("Saving invoice to Database: "+invoice);
    }
}
