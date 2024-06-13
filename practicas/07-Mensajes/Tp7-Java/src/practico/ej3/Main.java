package practico.ej3;

import practico.Channel;

public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel();
        int n = 10;
        Thread servidor = new Thread(new Servidor(channel));

        for (int i = 0; i < n; i++) {
            Thread cliente = new Thread(new Cliente(channel, i));
            cliente.start();
        }

        servidor.start();
    }
}
