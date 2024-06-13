package teorico.ejem2;

import practico.Channel;

import java.util.Random;

// Proceso P1
class A implements Runnable {
    private Channel c1;

    public A(Channel channel) {
        this.c1 = channel;
    }

    @Override
    public void run() {
        Integer r = new Random().nextInt();
        c1.send(r);
        System.out.println("A: Enviado r = " + r);
    }
}

