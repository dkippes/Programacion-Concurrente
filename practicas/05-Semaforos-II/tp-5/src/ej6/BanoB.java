package ej6;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class BanoB {

    public static void main(String[] args) throws InterruptedException {
        Semaphore permisoPersona = new Semaphore(8, true);
        Semaphore mutexP = new Semaphore(1, true);
        Semaphore permisoEntrar = new Semaphore(1, true);

        for (int i = 0; i < 100; i++) {
            int n = new Random().nextInt(2);
            if (n % 2 == 0) {
                Thread pl = new Thread(() -> {
                    //tiempoDeEspera(2000);
                    permisoEntrar.acquireUninterruptibly(); // Pide permiso para entrar al baño
                    permisoPersona.acquireUninterruptibly(8); // Roba el permiso a las personas
                    permisoEntrar.release();

                    System.out.println("Personal de limpieza limpiando el baño, hay " + permisoPersona.availablePermits() + " baños disponibles");
                    System.out.println("Personal de limpieza termino de limpiar el baño");

                    permisoPersona.release(8);
                });
                pl.start();
            } else {
                Thread persona = new Thread(() -> {
                    //tiempoDeEspera(500);
                    mutexP.acquireUninterruptibly(); // Esto evita que el personal de limpieza empere de mas (prioridad)
                    permisoEntrar.acquireUninterruptibly(); // Pide permiso para entrar al baño
                    permisoPersona.acquireUninterruptibly();
                    permisoEntrar.release();
                    mutexP.release();

                    System.out.println("Persona entrando al baño, hay " + permisoPersona.availablePermits() + " baños disponibles");
                    //tiempoDeEspera(1500);
                    permisoPersona.release();

                    System.out.println("Persona saliendo del baño");
                });
                persona.start();
            }
            //tiempoDeEspera(100);
        }
    }

    static void tiempoDeEspera(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Va a generar inanicion en el caso que indefinidamente el personal de limpieza venga y le robe el permiso
     * a la persona.
     */
}