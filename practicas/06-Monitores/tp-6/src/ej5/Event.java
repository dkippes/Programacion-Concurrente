package ej5;

public class Event {
    private int cantidadPublicaciones = 0;

    synchronized void publish() {
        System.out.println("-------- Hay nueva publicación --------");
        cantidadPublicaciones++;
        notifyAll();
    }

    synchronized void suscribe() throws InterruptedException {
        System.out.println("Hay nuevo sub");
        int current = cantidadPublicaciones; // Guarda el current para comparar después
        while (current == cantidadPublicaciones) {
            wait();
        }
    }
}
