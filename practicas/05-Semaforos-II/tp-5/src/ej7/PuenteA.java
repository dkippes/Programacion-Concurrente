package ej7;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class PuenteA {

    public static void main(String[] args) throws InterruptedException {
        Semaphore mutexNorte = new Semaphore(1, true);
        Semaphore mutexSur = new Semaphore(1, true);
        Semaphore permisoAutoNorte = new Semaphore(1, true);
        Semaphore permisoAutoSur = new Semaphore(1, true);
        AtomicInteger autosN = new AtomicInteger();
        AtomicInteger autosS = new AtomicInteger();

        for (int i = 0; i < 100; i++) {
            int direccion = new Random().nextInt(2);
            Thread auto = new Thread(() -> {
                if (direccion == 1) {
                    mutexNorte.acquireUninterruptibly();
                    autosN.incrementAndGet();
                    permisoAutoNorte.acquireUninterruptibly();
                    if (autosN.get() == 1) { // Primer auto en dirección Norte
                        permisoAutoSur.acquireUninterruptibly(); // Bloquea la dirección Sur
                    }
                    permisoAutoNorte.release();
                    mutexNorte.release();

                    System.out.println("Auto yendo al " + direccion);
                    tiempoDeEspera(1);
                    System.out.println("Auto llego al " + direccion + "\n-------------------");

                    mutexNorte.acquireUninterruptibly();
                    autosN.decrementAndGet();
                    if (autosN.get() == 0) { // Último auto en dirección Norte
                        permisoAutoSur.release(); // Libera la dirección Sur
                    }
                    mutexNorte.release();
                } else { // Prioridad auto 2
                    mutexSur.acquireUninterruptibly();
                    autosS.incrementAndGet();
                    if (autosS.get() == 1) { // Primer auto en dirección Sur
                        permisoAutoNorte.acquireUninterruptibly(); // Bloquea la dirección Norte
                    }
                    mutexSur.release();

                    System.out.println("Auto yendo al " + direccion);
                    tiempoDeEspera(1);
                    System.out.println("Auto llego al " + direccion + "\n-------------------");

                    mutexSur.acquireUninterruptibly();
                    autosS.decrementAndGet();
                    if (autosS.get() == 0) { // Último auto en dirección Sur
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