package practico.ej4.a;

import practico.Channel;
import practico.ej3.Request;

// Proceso P1
class Cliente implements Runnable {
    private Channel channelServidor;
    private Channel channelCliente;

    public Cliente(Channel channelServidor, Channel channelCliente) {
        this.channelServidor = channelServidor;
        this.channelCliente = channelCliente;
    }

    @Override
    public void run() {
        channelServidor.send("     msg       ");
        String msgTrim = (String) channelCliente.receive();
        System.out.println("Cliente recibió: " + msgTrim);
    }
}

