package teorico.ejem3;

import practico.Channel;

import java.util.Random;

// Proceso P1
class A implements Runnable {
    private Channel c1;
    private Channel c2;

    public A(Channel c1, Channel c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public void run() {
        Integer n = (Integer) c2.receive();
        for (int i = 0; i < n; i++) {
            Integer r = new Random().nextInt();
            c1.send(r);
            System.out.println("A: Enviado r = " + r);
        }
    }
}

