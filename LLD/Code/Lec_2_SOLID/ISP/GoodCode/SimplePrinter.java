package Lec_2_SOLID.ISP.GoodCode;

public class SimplePrinter implements Printer{
    /*
    ISP is satisfied when a class implements only those interfaces whose methods it actually needs, avoiding forced dependencies.
    */
    @Override
    public void print(Document doc) {
        System.out.println("Printing the document");
    }
}
