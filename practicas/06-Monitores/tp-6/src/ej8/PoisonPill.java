package ej8;

import ej3.Buffer;

public class PoisonPill implements Runnable {
    private int id;

    public PoisonPill(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            System.out.println("-----Lanzo PoisonPill " + id + "-----");
            throw new PoisonException();
        } catch (PoisonException e) {
        }
    }
}
