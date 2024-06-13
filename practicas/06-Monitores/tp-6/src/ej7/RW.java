package ej7;

import java.io.Serializable;

import static java.lang.Thread.sleep;

public class RW {
    private String data;
    private int writers = 0;
    private int readers = 0;

    synchronized void beginRead() throws InterruptedException {
        while (!(writers == 0)) {
            wait();
        }
        readers++;
        System.out.println("Empezo de leer");
    }

    synchronized void endRead() {
        System.out.println("Termino de leer");
        readers--;
        if (readers == 0) {
            notify();
        }
    }

    synchronized void beginWrite() throws InterruptedException {
        while (!(readers == 0 && writers == 0)) {
            wait();
        }
        System.out.println("Empezo a escribir");
        writers++;
    }

    synchronized void endWrite() {
        writers--;
        System.out.println("Termino de escribir");
        notifyAll();
    }

    public void serializar(String s) {
        System.out.println("Escribiendo -Serializando");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.data = s;
    }

    public String deserializar() {
        System.out.println("Leyendo -Deserializando");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
