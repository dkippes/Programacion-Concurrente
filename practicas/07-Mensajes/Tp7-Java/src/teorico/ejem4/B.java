package teorico.ejem4;

import practico.Channel;

// Proceso P2
class B implements Runnable {
    private Channel c1;
    private Channel c2;

    public B(Channel c1, Channel c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public void run() {
        Integer n = 10;
        c2.send(n);
        for (int i = 0; i < n; i++) {
            Integer r = (Integer) c1.receive();
            System.out.println("B: R = " + r);
        }
    }
}

