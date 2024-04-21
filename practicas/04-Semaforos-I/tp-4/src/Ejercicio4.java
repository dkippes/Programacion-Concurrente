import java.util.concurrent.Semaphore;

public class Ejercicio4 {
    public static void main(String[] args) {
        Semaphore permisoA = new Semaphore(1);
        Semaphore permisoF = new Semaphore(0);
        Semaphore permisoE = new Semaphore(1);
        Semaphore permisoH = new Semaphore(0);
        Semaphore permisoC = new Semaphore(1);
        Semaphore permisoG = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            while (true) {
                permisoA.acquireUninterruptibly();
                System.out.println("A");
                permisoF.release();
                System.out.println("B");
                permisoC.acquireUninterruptibly();
                System.out.println("C");
                permisoG.release();
                System.out.println("D");
            }
        });

        Thread T2 = new Thread(() -> {
            while (true) {
                permisoE.acquireUninterruptibly();
                System.out.println("E");
                permisoH.release();
                permisoF.acquireUninterruptibly();
                System.out.println("F");
                permisoA.release();
                System.out.println("G");
                permisoC.release();
            }
        });

        Thread T3 = new Thread(() -> {
            while (true) {
                permisoH.acquireUninterruptibly();
                System.out.println("H");
                permisoE.release();
                System.out.println("I");
            }
        });

        T1.start();
        T2.start();
        T3.start();
    }
}