package practico.ej4.b;

import practico.Channel;

// Proceso P1
class Cliente implements Runnable {
    private int id;
    private Channel channel;

    public Cliente(int id, Channel channel) {
        this.id = id;
        this.channel = channel;
    }

    @Override
    public void run() {
        Channel channelPrivado = new Channel();
        String msg = "    msg" + id + "     ";
        Request req = new Request(msg, channelPrivado);
        channel.send(req);
        String msgTrim = (String) channelPrivado.receive();
        System.out.println("Cliente " + id + " recibió: " + msgTrim);
    }
}

