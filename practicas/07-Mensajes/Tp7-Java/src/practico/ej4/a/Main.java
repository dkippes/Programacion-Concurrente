package practico.ej4.a;

import practico.Channel;

public class Main {
    public static void main(String[] args) {
        Channel channelServidor = new Channel();
        Channel channelCliente = new Channel();

        Thread servidor = new Thread(new Servidor(channelServidor, channelCliente));
        Thread cliente = new Thread(new Cliente(channelServidor, channelCliente));

        cliente.start();
        servidor.start();
    }
}
