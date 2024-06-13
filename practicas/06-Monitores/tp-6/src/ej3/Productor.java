package ej3;

public class Productor extends Thread {
    Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            buffer.producir(new Object());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
