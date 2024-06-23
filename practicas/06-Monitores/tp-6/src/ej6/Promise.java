package ej6;

public class Promise implements Future {
    private Object value;

    @Override
    public synchronized Object get() {
        while (value == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Se obtiene el valor + " + value);
        Object temp = value;
        value = null;
        return temp;
    }

    synchronized void set(Object value) {
        System.out.println("Se setea el valor + " + value);
        this.value = value;
        this.notifyAll();
    }
}
