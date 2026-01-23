package Lec_2_SOLID.LSP.GoodCode;

public class Main {

    // This method depends only on the ReadableFile abstraction.
    // Any class that implements ReadableFile can be safely passed here
    // without breaking behavior (LSP).
    public static void readAnyFile(ReadableFile file){
        file.read(); // Guaranteed to work for all ReadableFile implementations
    }

    public static void main(String[] args) {

        // ReadOnlyFile is assigned to a ReadableFile reference.
        // This is valid because ReadOnlyFile fulfills the ReadableFile contract.
        ReadableFile readableFile = new ReadOnlyFile();
        readableFile.read(); // Works as expected

        // WriteableFile supports both read and write operations.
        // It extends ReadableFile, so it can also be used wherever
        // a ReadableFile is required.
        WriteableFile writableFile = new WriteableFile();
        writableFile.read();  // Inherited read behavior
        writableFile.write(); // Additional behavior specific to writable files

        // LSP in action:
        // readAnyFile expects a ReadableFile, and both objects
        // (ReadOnlyFile and WriteableFile) can be substituted safely.
        readAnyFile(readableFile); // Works for ReadOnlyFile
        readAnyFile(writableFile); // Works for WriteableFile
    }
}
