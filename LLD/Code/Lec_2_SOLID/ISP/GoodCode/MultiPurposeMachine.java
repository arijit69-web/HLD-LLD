package Lec_2_SOLID.ISP.GoodCode;

public class MultiPurposeMachine implements Printer, Scanner, Copier {

    /*
     * GOOD ISP DESIGN:
     * The large Machine interface is split into smaller,
     * role-specific interfaces: Printer, Scanner, Copier.
     *
     * MultiPurposeMachine implements ONLY the capabilities
     * it actually supports.
     *
     * No unused methods.
     * No UnsupportedOperationException.
     * No runtime surprises.
     */

    // Implements Printer interface → valid responsibility
    @Override
    public void print(Document doc) {
        System.out.println("Printing document...");
    }

    // Implements Scanner interface → valid responsibility
    @Override
    public void scan(Document doc) {
        System.out.println("Scan document...");
    }

    // Implements Copier interface → valid responsibility
    @Override
    public void copy(Document doc) {
        System.out.println("Copy document...");
    }
}
