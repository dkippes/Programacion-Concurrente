package ej4.b;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Boliche {

    public static void main(String[] args) throws InterruptedException {
        Semaphore permEntrarH = new Semaphore(1, true);
        Semaphore permEntrarM = new Semaphore(1, true);
        Semaphore mutexSumaH = new Semaphore(1, true);
        Semaphore mutexSumaM = new Semaphore(1, true);
        AtomicInteger hombres = new AtomicInteger();
        AtomicInteger mujeres = new AtomicInteger();
        AtomicInteger capacidad = new AtomicInteger(50);

        while (true) {
            int n = new Random().nextInt(2);
            if (n % 2 == 0) {
                Thread hombre = new Thread(() -> {
                    mutexSumaH.acquireUninterruptibly();
                    if (hombres.get() != (capacidad.get() / 2)) {
                        hombres.incrementAndGet();
                        permEntrarH.acquireUninterruptibly();

                        System.out.println("Hombre entra al boliche a bailar, capacidad maxima " + hombres.get());
                        permEntrarM.release();
                    }
                    mutexSumaH.release();
                });
                hombre.start();
            } else {
                Thread mujere = new Thread(() -> {
                    mutexSumaM.acquireUninterruptibly();
                    if (mujeres.get() != (capacidad.get() / 2)) {
                        mujeres.incrementAndGet();
                        permEntrarM.acquireUninterruptibly();

                        System.out.println("Mujer entra al boliche a bailar, capacidad maxima " + mujeres.get());
                        permEntrarH.release();
                    }
                    mutexSumaM.release();
                });
                mujere.start();
            }
            tiempoDeEspera(100);
        }
    }

    static void tiempoDeEspera(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}