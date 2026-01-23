package Lec_2_SOLID.LSP.BadCode;

public class Main {
    public static void main(String[] args) {
        /*
        LSP => LSP states that a subclass should be substitutable for its superclass without altering the expected behavior of the program.
        ReadOnlyFile cannot substitute File : LSP Violates
         */
        File file = new ReadOnlyFile();
        file.read(); // works fine
        file.write(); // throwing an exception, violation of LSP | So to fix this code, we have to make an object that does not support this write() method.
        // If you cannot implement this write() method then why are you providing this method?
    }
}