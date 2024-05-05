package ej2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Ejercicio2BA {

    private static int N = 4;

    public static void main(String[] args) throws InterruptedException {
        // Bote
        Semaphore permisoViajar = new Semaphore(0);
        Semaphore permisoVolver = new Semaphore(0);
        // Persona
        Semaphore permisoSubir = new Semaphore(1);
        Semaphore permisoBajar = new Semaphore(0);

        Thread bote = new Thread(() -> {
            while (true) {
                permisoViajar.acquireUninterruptibly(); // Viaje al Este
                System.out.println("Viajando"); // Viajar de Este a Oeste
                tiempoDeEspera();
                permisoBajar.release();
                permisoVolver.acquireUninterruptibly();
                System.out.println("Volviendo"); // Volver
                permisoSubir.release();
            }
        });

        // Persona
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            Thread persona = new Thread(() -> {
                permisoSubir.acquireUninterruptibly();
                System.out.println("Persona " + finalI + " subiendo al bote"); // Subir al bote
                permisoViajar.release();
                permisoBajar.acquireUninterruptibly();
                System.out.println("Persona " + finalI + " bajando del bote"); // Bajar del bote
                permisoVolver.release();
            });
            persona.start();
        }
        bote.start();
    }

    static void tiempoDeEspera() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
