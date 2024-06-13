package ej6;

public class Promise implements Future {
    private Object value;
    private boolean estaDisponible;

    @Override
    public synchronized Object get() {
        while (!estaDisponible) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Se obtiene el valor + " + value);
        return value;
    }

    synchronized void set(Object value) {
        System.out.println("Se setea el valor + " + value);
        estaDisponible = true;
        this.value = value;
        this.notifyAll();
    }
}
