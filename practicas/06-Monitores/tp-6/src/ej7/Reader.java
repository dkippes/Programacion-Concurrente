package ej7;

public class Reader extends Thread {
    private int id;
    private RW rw;

    public Reader(int id, RW rw) {
        this.id = id;
        this.rw = rw;
    }

    @Override
    public void run() {
        try {
            rw.beginRead();
            System.out.println("Lector " + id + " leyendo: " + rw.deserializar());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        rw.endRead();
    }
}
