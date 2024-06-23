package practico.ej5;

import practico.Channel;

// Proceso P1
class Servidor implements Runnable {
    private Channel channel;

    public Servidor(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while(true) {
            Request req = (Request) channel.receive();
            System.out.println("Servidor recibió: " + req.msg);
            String msgTrim = req.msg.trim();
            req.channelPrivado.send(msgTrim);
        }
    }
}

