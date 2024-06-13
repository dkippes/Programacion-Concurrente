package practico.ej1.b;

import practico.Channel;

// Proceso P1
class Servidor implements Runnable {
    private Channel channel;

    public Servidor(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        int cantidad = 0;
        while (true) {
            Request req;
            req = (Request) channel.receive();
            if (req.tipo.equals("cuenta")) {
                req.channel.send(cantidad);
                cantidad = 0;
            }
            if (req.tipo.equals("sigue")) {
                cantidad++;
            }
        }
    }
}

