import java.util.concurrent.Semaphore;

public class Ejercicio7 {
    public static void main(String[] args) {
        solucionC();
    }

    private static void solucionA() {
        Semaphore permisoA = new Semaphore(1);
        Semaphore permisoB = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            while (true) {
                permisoA.acquireUninterruptibly();
                System.out.print("A");
                permisoB.release();
            }
        });

        Thread T2 = new Thread(() -> {
            while (true) {
                permisoB.acquireUninterruptibly();
                System.out.print("B");
                permisoA.release();
            }
        });

        T1.start();
        T2.start();
    }

    private static void solucionB() {
        Semaphore permisoA = new Semaphore(1);
        Semaphore permisoB = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            while (true) {
                permisoA.acquireUninterruptibly();
                System.out.print("A");
                permisoB.release();
            }
        });

        Thread T2 = new Thread(() -> {
            while (true) {
                permisoB.acquireUninterruptibly();
                System.out.print("B");
                permisoA.release();
            }
        });

        T1.start();
        T2.start();
    }

    private static void solucionC() {
        Semaphore permisoA = new Semaphore(2);
        Semaphore permisoB = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            while (true) {
                permisoA.acquireUninterruptibly(2);
                System.out.print("A");
                permisoB.release(2);
            }
        });
        Thread T2 = new Thread(() -> {
            while (true) {
                permisoB.acquireUninterruptibly();
                System.out.print("B");
                permisoA.release();
            }
        });

        T1.start();
        T2.start();
    }
}