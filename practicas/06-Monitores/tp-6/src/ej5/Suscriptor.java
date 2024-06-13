package ej5;

public class Suscriptor extends Thread {
    Event event;

    public Suscriptor(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        try {
            event.suscribe();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
