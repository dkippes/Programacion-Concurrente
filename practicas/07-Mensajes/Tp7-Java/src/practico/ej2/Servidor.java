package practico.ej2;

import practico.Channel;

// Proceso P1
class Servidor implements Runnable {
    private Channel c1;
    private Channel c2;

    public Servidor(Channel c1, Channel c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public void run() {
        String msg = "";
        while (true) {
            String msg1 = (String) c1.receive();
            String msg2 = (String) c2.receive();
            msg += " " + msg1 + " " + msg2;
            System.out.println("Servidor: Recibido msg = " + msg);
        }
    }
}

