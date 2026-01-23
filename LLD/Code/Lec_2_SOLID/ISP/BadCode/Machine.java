package Lec_2_SOLID.ISP.BadCode;

interface Machine {
    void print(Document doc);
    void scan(Document doc);
    void copy(Document doc);
}