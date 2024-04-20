import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Ejercicio2B {
    public static void main(String[] args) throws InterruptedException {
        solucionB();
    }

    private static void solucionB() throws InterruptedException {
        // Gente para este a oeste
        // Gente para oeste a este
        AtomicInteger costa = new AtomicInteger(); // Este 0, Oeste 1
        Semaphore capacidadBote = new Semaphore(4);
        AtomicInteger personaId = new AtomicInteger();
        Semaphore permisoSubir = new Semaphore(0);
        Semaphore permisoBajar = new Semaphore(0);
        Semaphore permisoViajando = new Semaphore(0);
        Semaphore personaMutex = new Semaphore(1);
        Semaphore boteMutex = new Semaphore(1);
        Semaphore permisoVolverALaCosta = new Semaphore(0);

        int[] personasEste = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] personasOeste = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        Thread bote = new Thread(() -> {
            while (true) {
                if (costa.get() == 0) {
                    // Este
                    System.out.println("Bote se mueve a la costa Oeste");
                    capacidadBote.acquireUninterruptibly();
                }
                if (costa.get() == 1) {
                    // Oeste
                    System.out.println("Bote se mueve a la costa Este");
                    capacidadBote.acquireUninterruptibly();
                }
                boteMutex.acquireUninterruptibly();
                permisoSubir.acquireUninterruptibly();
                System.out.println("Bote se mueve a la costa " + costa);
                viajarEnBote();
                costa.set(1 - costa.get());
                permisoViajando.release();
                permisoBajar.acquireUninterruptibly();
                System.out.println("Bote se mueve a la costa " + costa);
                boteMutex.release();
                permisoVolverALaCosta.release();
            }
        });

        Thread persona = new Thread(() -> {
            while (true) {
                if (costa.get() == 0) {
                    // Este
                    System.out.println("Persona " + personaId + " se sube al bote");
                    capacidadBote.acquireUninterruptibly();
                }
                if (costa.get() == 1) {
                    // Oeste
                    capacidadBote.acquireUninterruptibly();
                }
            }
        });

        bote.start();
        persona.start();
    }

    private static void viajarEnBote() {
        try {
            sleep(1000);
            System.out.println("Viajando en bote");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}