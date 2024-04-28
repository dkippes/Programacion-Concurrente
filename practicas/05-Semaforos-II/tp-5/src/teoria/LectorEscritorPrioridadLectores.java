package teoria;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class LectorEscritorPrioridadLectores {

    private static int N = 4;

    public static void main(String[] args) throws InterruptedException {
        Semaphore permisoEscritor = new Semaphore(1);
        Semaphore mutexLector = new Semaphore(1);
        Semaphore mutexPermiso = new Semaphore(1);
        AtomicInteger lectores = new AtomicInteger();

        // Los escritores pueden sufrir starvation
        for (int i = 0; i < N; i++) {
            Thread escritor = new Thread(() -> {
                mutexPermiso.acquireUninterruptibly();
                permisoEscritor.acquireUninterruptibly();
                escribir();
                permisoEscritor.release();
                mutexPermiso.release();
            });

            Thread lector = new Thread(() -> {
                mutexLector.acquireUninterruptibly();
                lectores.getAndIncrement();
                if (lectores.get() == 1) {
                    permisoEscritor.acquireUninterruptibly();
                }
                mutexLector.release();

                leer();

                mutexLector.acquireUninterruptibly();
                lectores.getAndDecrement();
                if (lectores.get() == 0) {
                    permisoEscritor.release();
                }
                mutexLector.release();
            });
            escritor.start();
            lector.start();
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
