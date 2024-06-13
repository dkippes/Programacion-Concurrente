package ej3;

public class Consumidor extends Thread {
    Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            buffer.consumir();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
