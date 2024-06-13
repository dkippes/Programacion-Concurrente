package ej7;

import java.util.Random;

public class Writer extends Thread {
    private int id;
    private RW rw;

    public Writer(int id, RW rw) {
        this.id = id;
        this.rw = rw;
    }

    @Override
    public void run() {
        try {
            rw.beginWrite();
            rw.serializar("Info Escritor: " + id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        rw.endWrite();
    }
}
