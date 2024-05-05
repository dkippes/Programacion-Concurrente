package ej6;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class BanoA {

    public static void main(String[] args) throws InterruptedException {
        Semaphore toilet = new Semaphore(8, true);

        while (true) {
            int n = new Random().nextInt(2);
            if (n % 2 == 0) {
                Thread personalLimpieza = new Thread(() -> {
                    tiempoDeEspera(2000);
                    toilet.acquireUninterruptibly(8);
                    System.out.println("Personal de limpieza limpiando el baño, hay " + toilet.availablePermits() + " baños disponibles");
                    tiempoDeEspera(3000);
                    System.out.println("Personal de limpieza termino de limpiar el baño");
                    toilet.release(8);
                });
                personalLimpieza.start();
            } else {
                Thread persona = new Thread(() -> {
                    tiempoDeEspera(500);
                    toilet.acquireUninterruptibly();
                    System.out.println("Persona entrando al baño, hay " + toilet.availablePermits() + " baños disponibles");
                    tiempoDeEspera(1500);
                    System.out.println("Persona saliendo del baño");
                    toilet.release();
                });
                persona.start();
            }
            tiempoDeEspera(100);
        }
    }

    static void tiempoDeEspera(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}