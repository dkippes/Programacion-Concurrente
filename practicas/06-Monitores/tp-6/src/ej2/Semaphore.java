package ej2;

public class Semaphore {
    int contador = 0;

    public Semaphore(int contador) {
        this.contador = contador;
    }

    synchronized void release() {
        this.contador = this.contador + 1;
    }

    synchronized void acquire() throws InterruptedException {
        while (this.contador == 0) {
            System.out.println("---Esperando---");
            this.wait();
        }
        this.contador = this.contador - 1;
    }
}
