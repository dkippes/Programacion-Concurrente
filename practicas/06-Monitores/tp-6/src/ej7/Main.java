package ej7;

import ej6.Promise;

public class Main {
    public static void main(String[] args) {
        RW rw = new RW();
        Writer writer1 = new Writer(1, rw);
        Writer writer2 = new Writer(2, rw);
        Reader reader1 = new Reader(3, rw);
        Reader reader2 = new Reader(4, rw);
        Reader reader3 = new Reader(5, rw);
        Reader reader4 = new Reader(6, rw);

        writer1.start();
        reader1.start();
        reader2.start();
        writer2.start();
        reader3.start();
        reader4.start();
    }
}
