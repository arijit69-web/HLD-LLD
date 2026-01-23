package Lec_2_SOLID.LSP.GoodCode;

public class WriteableFile extends ReadableFile implements Writeable{
    @Override
    public void write() {
        System.out.println("Writing to file...");
    }
    @Override
    public void read() {
        System.out.println("Reading from the Read-Write file...");
    }
}
