package Lec_2_SOLID.LSP.BadCode;

public class ReadOnlyFile extends File{
    public void write(){
        /*
         Problem : ReadOnly File is forced to implement this write() function | It should not implement the write() function
        */
        throw new UnsupportedOperationException("Can't write to a read only file");
        /*
        write() method is not supported in the Read-Only File. So if someone call this method we should throw a runtime exception
         */
    }
}