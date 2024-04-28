package teoria;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class LectorEscritorPrioridadEscritores {

    private static int N = 4;

    public static void main(String[] args) throws InterruptedException {
        Semaphore permisoEscritor = new Semaphore(1);
        Semaphore permisoLector = new Semaphore(1);
        Semaphore mutexLector = new Semaphore(1);
        Semaphore mutexPermiso = new Semaphore(1);
        Semaphore mutexEscritor = new Semaphore(1);
        AtomicInteger lectores = new AtomicInteger();
        AtomicInteger escritores = new AtomicInteger();

        // Los lectores pueden sufrir starvation
        for (int i = 0; i < N; i++) {
            Thread lector = new Thread(() -> {
                mutexPermiso.acquireUninterruptibly();
                mutexLector.acquireUninterruptibly();
                permisoLector.acquireUninterruptibly();
                lectores.getAndIncrement();
                if (lectores.get() == 1) {
                    permisoEscritor.acquireUninterruptibly();
                }

                permisoLector.release();
                mutexLector.release();
                mutexPermiso.release();

                leer();

                mutexLector.acquireUninterruptibly();
                lectores.getAndDecrement();
                if (lectores.get() == 0) {
                    permisoEscritor.release();
                }
                mutexLector.release();
            });

            Thread escritor = new Thread(() -> {
                mutexEscritor.acquireUninterruptibly();
                escritores.getAndIncrement();
                if (escritores.get() == 1) {
                    permisoLector.acquireUninterruptibly();
                }
                mutexEscritor.release();

                permisoEscritor.acquireUninterruptibly();
                escribir();
                permisoEscritor.release();

                mutexEscritor.acquireUninterruptibly();
                escritores.getAndDecrement();
                if (escritores.get() == 0) {
                    permisoLector.release();
                }
                mutexEscritor.release();
            });
            lector.start();
            escritor.start();
        }
    }

    private static void escribir() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Escribiendo...");
    }

    private static void leer() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Leyendo...");
    }
}
