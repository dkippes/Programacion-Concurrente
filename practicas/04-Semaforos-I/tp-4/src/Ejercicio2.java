import java.util.concurrent.Semaphore;

public class Ejercicio2 {
    public static void main(String[] args) throws InterruptedException {
        Semaphore permisoC = new Semaphore(0);
        Semaphore permisoR = new Semaphore(0);
        Semaphore permisoO = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            permisoC.acquireUninterruptibly();
            System.out.print("C");
            permisoR.release();
            System.out.print("E");
            permisoO.release();
        });

        Thread T2 = new Thread(() -> {
            System.out.print("A");
            permisoC.release();
            permisoR.acquireUninterruptibly();
            System.out.print("R");
            permisoO.acquireUninterruptibly();
            System.out.print("O");
        });

        T1.start();
        T2.start();

        T1.join();
        T2.join();
    }
}