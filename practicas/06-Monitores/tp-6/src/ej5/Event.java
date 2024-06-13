package ej5;

public class Event {
    private boolean published = false;
    private int suscriptors = 0;

    synchronized void publish() {
        published = true;
        for (int i = 0; i < suscriptors; i++) {
            this.notify();
        }
        System.out.println("Publicado");
        suscriptors = 0;
    }

    synchronized void suscribe() throws InterruptedException {
        suscriptors++;
        System.out.println("Suscrito N°" + suscriptors);
        while (!published) {
            this.wait();
        }
        published = false;
    }
}
