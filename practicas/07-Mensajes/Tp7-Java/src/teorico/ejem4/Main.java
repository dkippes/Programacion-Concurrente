package teorico.ejem4;


import practico.Channel;

public class Main {
    public static void main(String[] args) {
        // Crear los canales de comunicación
        Channel c1 = new Channel();
        Channel c2 = new Channel();

        // Crear y lanzar los procesos P1, P2 y P3
        Thread a = new Thread(new A(c1, c2));
        Thread b = new Thread(new B(c1, c2));

        b.start();
        a.start();
    }
}