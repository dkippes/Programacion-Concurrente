import org.junit.Test;

import java.util.concurrent.Semaphore;

import static org.junit.Assert.assertTrue;

public class Ejercicio2Test {

    @Test
    public void testPossibleOutputs() throws InterruptedException {
        StringBuilder output = new StringBuilder();

        // Mock del System.out para capturar la salida
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()) {
            @Override
            public void println(String x) {
                output.append(x);
            }
        });

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

        T2.start();
        T1.start();

        // Esperamos un poco para que las hebras terminen de ejecutarse
        Thread.sleep(1000);

        // Verificamos las posibles salidas
        String outputString = output.toString();
        assertTrue(outputString.equals("ACERO") || outputString.equals("ACREO"));
    }
}
