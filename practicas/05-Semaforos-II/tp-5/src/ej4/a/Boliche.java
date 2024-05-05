package ej4.a;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Boliche {

    public static void main(String[] args) throws InterruptedException {
        Semaphore permEntrarH = new Semaphore(1, true);
        Semaphore permEntrarM = new Semaphore(1, true);
        // Al estar en 1, se garantiza que primero entre una mujer y luego un hombre o viceversa
        // Si se cambia a 0, se garantiza que primero entre un hombre y luego una mujer

        while (true) {
            int n = new Random().nextInt(2);
            if (n % 2 == 0) {
                Thread hombre = new Thread(() -> {
                    permEntrarH.acquireUninterruptibly();
                    System.out.println("Hombre entra al boliche a bailar");
                    permEntrarM.release();

                    while (true) {
                        System.out.println("Hombre baila");
                        tiempoDeEspera();
                    }
                });
                hombre.start();
            } else {
                Thread mujeres = new Thread(() -> {
                    permEntrarM.acquireUninterruptibly();
                    System.out.println("Mujer entra al boliche a bailar");
                    permEntrarH.release();

                    while (true) {
                        System.out.println("Mujer baila");
                        tiempoDeEspera();
                    }
                });
                mujeres.start();
            }
            tiempoDeEspera();
        }
    }

    static void tiempoDeEspera() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}