package practico.ej1.a;

import practico.Channel;

import java.util.Random;

// Proceso P1
class Cliente implements Runnable {
    private Channel channel;

    public Cliente(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            Integer random = new Random().nextInt(10);
            if (random % 2 == 0) {
                channel.send("cuenta");
            } else {
                channel.send("sigue");
            }
        }
    }
}

