package teorico.ejem1;

import practico.Channel;

// Proceso P3
class P3 implements Runnable {
    private Channel channel1;
    private Channel channel2;

    public P3(Channel channel1, Channel channel2) {
        this.channel1 = channel1;
        this.channel2 = channel2;
    }

    @Override
    public void run() {
        int r1 = (int) channel1.receive();
        int r2 = (int) channel2.receive();
        System.out.println("P3: Recibido r1 = " + r1 + ", r2 = " + r2);
        System.out.println("P3: Resultado = " + (r1 + r2));
    }
}

