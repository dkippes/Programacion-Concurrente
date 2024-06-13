package ej8;

public class BufferT {
    private Runnable[] buffer;
    private int inicio = 0;
    private int fin = 0;

    public BufferT(int tamano) {
        this.buffer = new Runnable[tamano+1];
    }

    public synchronized void producir(Runnable o) throws InterruptedException {
        System.out.println("Llega nuevo productor");
        while (estaLleno()) {
            System.out.println("Esta lleno ---Esperando consumidor---");
            this.wait();
        }
        this.buffer[inicio] = o;
        this.inicio = proximo(inicio);
        System.out.println("Produce objeto");
        this.notifyAll();
    }

    public synchronized Runnable consumir() throws InterruptedException {
        System.out.println("Llega nuevo consumidor");
        while (estaVacio()) {
            System.out.println("Esta vacio ---Esperando productor---");
            this.wait();
        }
        Runnable result = buffer[fin];
        fin = proximo(fin);
        System.out.println("Consume objeto");
        notifyAll();
        return result;
    }

    private boolean estaVacio() {
        return inicio == fin;
    }

    private boolean estaLleno() {
        return proximo(inicio) == fin;
    }

    private int proximo(int i) {
        return (i+1)%(buffer.length);
    }
}
