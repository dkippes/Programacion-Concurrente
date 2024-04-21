import java.util.concurrent.Semaphore;

// TODO: Duda respecto al ejercicio
public class Ejercicio5 {
    static int x = 0;
    public static void main(String[] args) {
        Semaphore permiso2 = new Semaphore(0);
        Semaphore permiso3 = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            x = x + 1;
            permiso2.release();
        });

        Thread T2 = new Thread(() -> {
            permiso2.acquireUninterruptibly();
            x = x + 2;
            permiso3.release();
        });

        Thread T3 = new Thread(() -> {
            permiso3.acquireUninterruptibly();
            x = x + 3;
        });

        T1.start();
        T2.start();
        T3.start();

        System.out.println(x);
    }
}