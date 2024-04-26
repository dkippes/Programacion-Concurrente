package ej2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Ejercicio2A {
    public static void main(String[] args) throws InterruptedException {
        solucionA();
    }

    private static void solucionA() throws InterruptedException {
        AtomicInteger costa = new AtomicInteger(); // Este 0, Oeste 1
        AtomicInteger personaId = new AtomicInteger();
        Semaphore permisoSubir = new Semaphore(0);
        Semaphore permisoBajar = new Semaphore(0);
        Semaphore permisoViajando = new Semaphore(0);
        Semaphore personaMutex = new Semaphore(1);
        Semaphore boteMutex = new Semaphore(1);
        Semaphore permisoVolverALaCosta = new Semaphore(0);
        int[] personas = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Thread bote = new Thread(() -> {
            while (true) {
                boteMutex.acquireUninterruptibly();
                permisoSubir.acquireUninterruptibly();
                System.out.println("Bote se mueve a la costa " + costa);
                viajarEnBote();
                costa.set(1 - costa.get());
                permisoViajando.release();
                permisoBajar.acquireUninterruptibly();
                System.out.println("Bote se mueve a la costa " + costa);
                costa.set(1 - costa.get());
                boteMutex.release();
                permisoVolverALaCosta.release();
            }
        });

        Thread persona = new Thread(() -> {
            while (true) {
                personaMutex.acquireUninterruptibly();
                System.out.println("Persona " + personaId + " se sube al bote");
                permisoSubir.release();
                permisoViajando.acquireUninterruptibly();
                System.out.println("Persona " + personaId + " se baja del bote");
                permisoBajar.release();
                personaId.getAndIncrement();
                permisoVolverALaCosta.acquireUninterruptibly();
                personaMutex.release();
            }
        });

        bote.start();
        persona.start();

        bote.join();
        persona.join();
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