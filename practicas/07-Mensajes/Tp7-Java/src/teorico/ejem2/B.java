package teorico.ejem2;

import practico.Channel;

// Proceso P2
class B implements Runnable {
    private Channel c1;

    public B(Channel channel) {
        this.c1 = channel;
    }

    @Override
    public void run() {
        Integer r = (Integer) c1.receive();
        System.out.println("B: R = " + r);
    }
}

