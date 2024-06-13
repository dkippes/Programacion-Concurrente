package teorico.ejem1;

import practico.Channel;

// Proceso P1
class P1 implements Runnable {
    private Channel channel;

    public P1(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        int x = 10;
        int r1 = compute(x);
        channel.send(r1);
        System.out.println("P1: Enviado r1 = " + r1);
    }

    private int compute(int x) {
        return x * 2;
    }
}

