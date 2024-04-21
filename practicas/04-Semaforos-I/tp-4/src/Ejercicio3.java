import java.util.concurrent.Semaphore;

public class Ejercicio3 {
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Semaphore permisoI = new Semaphore(0);
        Semaphore permisoO = new Semaphore(0);
        Semaphore permisoOk = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            print("R");
            permisoI.release();
            permisoOk.acquireUninterruptibly();
            print("OK");
        });

        Thread T2 = new Thread(() -> {
            permisoI.acquireUninterruptibly();
            print("I");
            permisoO.release();
            permisoOk.acquireUninterruptibly();
            print("OK");
        });

        Thread T3 = new Thread(() -> {
            permisoO.acquireUninterruptibly();
            print("O");
            permisoOk.release(2);
            print("OK");
        });

        T1.start();
        T2.start();
        T3.start();
    }

    private static void print(String texto) {
        synchronized (lock) {
            System.out.println(texto);
        }
    }
}