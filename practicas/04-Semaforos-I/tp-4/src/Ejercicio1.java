import java.util.concurrent.Semaphore;

public class Ejercicio1 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore permisoF = new Semaphore(0);
        Semaphore permisoC = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            System.out.println("A");
            permisoF.release();
            System.out.println("B");
            permisoC.acquireUninterruptibly();
            System.out.println("C");
        });

        Thread T2 = new Thread(() -> {
            System.out.println("E");
            permisoF.acquireUninterruptibly();
            System.out.println("F");
            permisoC.release();
            System.out.println("G");
        });

        T1.start();
        T2.start();

        T1.join();
        T2.join();
    }
}