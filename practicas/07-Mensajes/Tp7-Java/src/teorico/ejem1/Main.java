package teorico.ejem1;

import practico.Channel;

public class Main {
    public static void main(String[] args) {
        // Crear los canales de comunicación
        Channel c1 = new Channel();
        Channel c2 = new Channel();

        // Crear y lanzar los procesos P1, P2 y P3
        Thread p1 = new Thread(new P1(c1));
        Thread p2 = new Thread(new P2(c2));
        Thread p3 = new Thread(new P3(c1, c2));

        p1.start();
        p2.start();
        p3.start();
    }
}