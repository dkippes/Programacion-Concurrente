import java.util.concurrent.Semaphore;

public class Ejercicio6 {
    static int y = 0, z = 0, x;
    public static void main(String[] args) throws InterruptedException {
        valor0();
    }

    private static void valor3() {
        Semaphore permisoX = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            permisoX.acquireUninterruptibly();
            x = y + z;
        });

        Thread T2 = new Thread(() -> {
            y = 1;
            z = 2;
            permisoX.release();
        });

        T1.start();
        T2.start();

        System.out.println(x);
    }

    private static void valor1() {
        Semaphore permisoX = new Semaphore(0);
        Semaphore permisoY = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            permisoX.acquireUninterruptibly();
            x = y + z;
            permisoY.release();
        });

        Thread T2 = new Thread(() -> {
            y = 1;
            permisoX.release();
            permisoY.acquireUninterruptibly();
            z = 2;
        });

        T1.start();
        T2.start();

        System.out.println(x);
    }

    private static void valor0() {
        Semaphore permisoYZ = new Semaphore(0);

        Thread T1 = new Thread(() -> {
            x = y + z;
            permisoYZ.release();
        });

        Thread T2 = new Thread(() -> {
            permisoYZ.acquireUninterruptibly();
            y = 1;
            z = 2;
        });

        T1.start();
        T2.start();

        System.out.println(x);
    }

    /*
    * a) Cuales son los posibles valores finales de x?
    * x puede ser = {0, 1, 3}
    *
    * b) Para cada uno de los valores finales de x posibles, modifique el programa usando sem ́aforos
    * de forma tal que siempre x tenga ese valor al final de la ejecuci ́on (considere que el programa
    * modificado siempre debe poder terminar).
    *
    *
    * */
}