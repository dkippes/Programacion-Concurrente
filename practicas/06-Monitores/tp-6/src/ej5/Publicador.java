package ej5;

public class Publicador extends Thread {
    Event event;

    public Publicador(Event event) {
        this.event = event;
    }

    @Override
    public void run() {
        event.publish();
    }
}
