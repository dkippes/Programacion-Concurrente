package ej1;

import java.util.concurrent.Semaphore;

// Arreglar los 50 N impares -
public class Ejercicio1 {
    private static int N = 3; // 9
    private static int sum = 0;

    public static void main(String[] args) {
        Semaphore permSiguienteNImpar = new Semaphore(1);
        Semaphore permSumarImpar = new Semaphore(0);

        Thread generar = new Thread(() -> {
            while (N > 0) {
                permSiguienteNImpar.acquireUninterruptibly();
                N--;
                permSumarImpar.release();
            }
            System.out.println("Suma: " + sum);
        });

        Thread sumar = new Thread(() -> {
            while (true) {
                permSumarImpar.acquireUninterruptibly();
                sum = sum + 2 * N + 1;
                permSiguienteNImpar.release();
            }
        });

        generar.start();
        sumar.start();
    }
}