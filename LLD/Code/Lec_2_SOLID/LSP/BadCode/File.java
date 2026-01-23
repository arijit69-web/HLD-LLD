package Lec_2_SOLID.LSP.BadCode;

public class File {// Files can be both readonly and read-write files
    public void read(){
        System.out.println("Reading from file...");
    }
    public void write(){
        System.out.println("Writing to file...");
    }
}
