package practico.ej3;

import practico.Channel;

// Proceso P1
class Cliente implements Runnable {
    private Channel channel;
    private int numero;

    public Cliente(Channel channel, int numero) {
        this.channel = channel;
        this.numero = numero;
    }

    @Override
    public void run() {
        Channel channelPrivado = new Channel();
        Request request = new Request(numero, channelPrivado);
        channel.send(request);
        String respuesta = (String) channelPrivado.receive();
        System.out.println(respuesta);
    }
}

