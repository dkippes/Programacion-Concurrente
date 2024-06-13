package teorico.ejem1;

import practico.Channel;

// Proceso P2
class P2 implements Runnable {
    private Channel channel;

    public P2(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        int y = 20;
        int r2 = compute(y);
        channel.send(r2);
        System.out.println("P2: Enviado r2 = " + r2);
    }

    private int compute(int y) {
        return y * 3;
    }
}

