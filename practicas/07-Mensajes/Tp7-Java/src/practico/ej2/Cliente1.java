package practico.ej2;

import practico.Channel;

// Proceso P1
class Cliente1 implements Runnable {
    private Channel c1;

    public Cliente1(Channel c1) {
        this.c1 = c1;
    }

    @Override
    public void run() {
        while (true) {
            String msg = "Hola";
            c1.send(msg);
        }
    }

}

