package practico.ej3;

import practico.Channel;

// Proceso P1
class Servidor implements Runnable {
    private Channel channel;

    public Servidor(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            Request request = (Request) channel.receive();
            request.channelPrivado.send("Hola cliente " + request.numero);
        }
    }
}

