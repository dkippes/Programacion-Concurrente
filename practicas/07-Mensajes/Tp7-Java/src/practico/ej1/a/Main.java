package practico.ej1.a;

import practico.Channel;

public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel();

        Thread servidor = new Thread(new Servidor(channel));
        Thread cliente = new Thread(new Cliente(channel));

        servidor.start();
        cliente.start();
    }
}
