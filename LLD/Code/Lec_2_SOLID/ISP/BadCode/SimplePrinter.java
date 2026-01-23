package Lec_2_SOLID.ISP.BadCode;

public class SimplePrinter implements Machine {
    /*
    ISP violation occurs when a class is forced to implement methods, it does not use, resulting in unused methods and runtime failures.
    */

    // SimplePrinter supports printing, so this method is valid
    @Override
    public void print(Document doc) {
        System.out.println("Printing document...");
    }

    /*
     * ISP VIOLATION:
     * SimplePrinter does NOT support scanning.
     * But the Machine interface forces it to implement scan().
     * This creates unnecessary dependency on functionality
     * that this class does not need.
     */
    @Override
    public void scan(Document doc) {
        throw new UnsupportedOperationException("Scan not supported.");
    }

    /*
     * ISP VIOLATION:
     * SimplePrinter does NOT support copying.
     * Forced implementation leads to runtime exceptions.
     * Clients depending on Machine may break at runtime.
     */
    @Override
    public void copy(Document doc) {
        throw new UnsupportedOperationException("Copy not supported.");
    }
}
