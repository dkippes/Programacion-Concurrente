package practico.ej2;

import practico.Channel;

// Proceso P1
class Cliente2 implements Runnable {
    private Channel c2;

    public Cliente2(Channel c2) {
        this.c2 = c2;
    }

    @Override
    public void run() {
        while (true) {
            String msg = "Chau";
            c2.send(msg);
        }
    }
}

