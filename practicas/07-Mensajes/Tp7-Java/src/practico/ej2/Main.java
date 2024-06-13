package practico.ej2;


import practico.Channel;

public class Main {
    public static void main(String[] args) {
        // Crear los canales de comunicación
        Channel c1 = new Channel();
        Channel c2 = new Channel();

        // Crear y lanzar los procesos P1, P2 y P3
        Thread cliente1 = new Thread(new Cliente1(c1));
        Thread cliente2 = new Thread(new Cliente2(c2));
        Thread servidor = new Thread(new Servidor(c1, c2));

        cliente1.start();
        cliente2.start();
        servidor.start();
    }
}