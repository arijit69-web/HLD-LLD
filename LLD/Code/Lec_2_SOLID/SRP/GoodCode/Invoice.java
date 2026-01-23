package Lec_2_SOLID.SRP.GoodCode;

public class Invoice {
    private double amount;

    public Invoice(double amount){
        this.amount = amount;
    }
    public void generateInvoice(){
        System.out.println("Invoice generated & printed for amount " +amount);
    }

    /*
    So we should create two separate classes, each handling a single responsibility.
    */
}