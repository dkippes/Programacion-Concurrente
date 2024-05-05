package ej7;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class PuenteB {

    public static void main(String[] args) throws InterruptedException {
        Semaphore mutexNorte = new Semaphore(1, true);
        Semaphore mutexSur = new Semaphore(1, true);
        Semaphore permisoAutoNorte = new Semaphore(1, true);
        Semaphore permisoAutoSur = new Semaphore(1, true);
        Semaphore permisoEntrarAutoSur = new Semaphore(3, true);
        Semaphore permisoEntrarAutoNorte = new Semaphore(3, true);
        AtomicInteger autosNorte = new AtomicInteger();
        AtomicInteger autosSur = new AtomicInteger();

        for (int i = 0; i < 100; i++) {
            int direccion = new Random().nextInt(2);
            Thread auto = new Thread(() -> {
                if (direccion == 1) {
                    mutexNorte.acquireUninterruptibly();
                    autosNorte.incrementAndGet();
                    permisoAutoNorte.acquireUninterruptibly();
                    if (autosNorte.get() == 1) { // Primer auto en dirección Norte
                        permisoAutoSur.acquireUninterruptibly(); // Bloquea la dirección Sur
                    }
                    permisoAutoNorte.release();
                    mutexNorte.release();

                    permisoEntrarAutoNorte.acquireUninterruptibly();
                    System.out.println("Auto yendo al " + direccion);
                    tiempoDeEspera(1);
                    System.out.println("Auto llego al " + direccion + "\n-------------------");
                    permisoEntrarAutoNorte.release();

                    mutexNorte.acquireUninterruptibly();
                    autosNorte.decrementAndGet();
                    if (autosNorte.get() == 0) { // Último auto en dirección Norte
                        permisoAutoSur.release(); // Libera la dirección Sur
                    }
                    mutexNorte.release();
                } else { // Prioridad auto 2
                    mutexSur.acquireUninterruptibly();
                    autosSur.incrementAndGet();
                    if (autosSur.get() == 1) { // Primer auto en dirección Sur
                        permisoAutoNorte.acquireUninterruptibly(); // Bloquea la dirección Norte
                    }
                    mutexSur.release();

                    permisoEntrarAutoSur.acquireUninterruptibly();
                    System.out.println("Auto yendo al " + direccion);
                    tiempoDeEspera(1);
                    System.out.println("Auto llego al " + direccion + "\n-------------------");
                    permisoEntrarAutoSur.release();

                    mutexSur.acquireUninterruptibly();
                    autosSur.decrementAndGet();
                    if (autosSur.get() == 0) { // Último auto en dirección Sur
                        permisoAutoNorte.release(); // Libera la dirección Norte
                    }
                    mutexSur.release();
                }
            });
            auto.start();
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