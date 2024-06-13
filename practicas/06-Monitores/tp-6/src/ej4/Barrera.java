package ej4;

public class Barrera {
    private int cantidadDeEsperas;

    public Barrera(int cantidadDeEsperas) {
        this.cantidadDeEsperas = cantidadDeEsperas;
    }

    synchronized void esperar() throws InterruptedException {
        cantidadDeEsperas--;
        while (cantidadDeEsperas > 0) {
            this.wait();
        }
        this.notifyAll();
    }
}
