package practico.ej4.a;

import practico.Channel;
import practico.ej3.Request;

// Proceso P1
class Servidor implements Runnable {
    private Channel channelServidor;
    private Channel channelCliente;

    public Servidor(Channel channelServidor, Channel channelCliente) {
        this.channelServidor = channelServidor;
        this.channelCliente = channelCliente;
    }

    @Override
    public void run() {
        while(true) {
            String msg = (String) channelServidor.receive();
            System.out.println("Servidor recibió: " + msg);
            String msgTrim = msg.trim();
            channelCliente.send(msgTrim);
        }
    }
}

