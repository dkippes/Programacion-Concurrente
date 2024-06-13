package teorico.ejem2;


import practico.Channel;

public class Main {
    public static void main(String[] args) {
        // Crear los canales de comunicación
        Channel c1 = new Channel();

        // Crear y lanzar los procesos P1, P2 y P3
        Thread a = new Thread(new A(c1));
        Thread b = new Thread(new B(c1));

        a.start();
        b.start();
    }
}