import java.util.concurrent.Semaphore;

public class Ejercicio4 {
    private static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Semaphore permisoA = new Semaphore(1);
        Semaphore permisoF = new Semaphore(0);
        Semaphore permisoE = new Semaphore(1);
        Semaphore permisoH = new Semaphore(0);
        Semaphore permisoC = new Semaphore(1);
        Semaphore permisoG = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            while (true) {
                permisoA.acquireUninterruptibly();
                print("A");
                permisoF.release();
                print("B");
                permisoC.acquireUninterruptibly();
                print("C");
                permisoG.release();
                print("D");
            }
        });

        Thread T2 = new Thread(() -> {
            while (true) {
                permisoE.acquireUninterruptibly();
                print("E");
                permisoH.release();
                permisoF.acquireUninterruptibly();
                print("F");
                permisoA.release();
                print("G");
                permisoC.release();
            }
        });

        Thread T3 = new Thread(() -> {
            while (true) {
                permisoH.acquireUninterruptibly();
                print("H");
                permisoE.release();
                print("I");
            }
        });

        T1.start();
        T2.start();
        T3.start();

        T1.join();
        T2.join();
        T3.join();
    }

    private static void print(String texto) {
        synchronized (lock) {
            System.out.println(texto);
        }
    }
}