import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

// Arreglar los 50 N impares -
public class Ejercicio1 {
    private static int N = 5;
    private static int sum = 0;
    public static void main(String[] args) throws InterruptedException {
        Semaphore permisoGenerarImpar = new Semaphore(0);
        Semaphore permisoSumarImpar = new Semaphore(0);
        Semaphore permisoTerminoCiclo = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            while (N > 0) {
                if (N % 2 != 0) {
                    permisoSumarImpar.release();
                    permisoGenerarImpar.acquireUninterruptibly();
                    permisoTerminoCiclo.release();
                }
                N--;
            }
        });

        Thread T2 = new Thread(() -> {
            while (N > 0) {
                permisoSumarImpar.acquireUninterruptibly();
                sum += N;
                permisoGenerarImpar.release();
                permisoTerminoCiclo.acquireUninterruptibly();
            }
        });

        T1.start();
        T2.start();

        System.out.println("Suma: " + sum);
    }
}