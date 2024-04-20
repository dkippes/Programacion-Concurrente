import org.junit.Test;

import java.util.concurrent.Semaphore;

import static org.junit.Assert.assertTrue;

public class Ejercicio1Test {

    @Test
    public void testOrder() throws InterruptedException {
        StringBuilder output = new StringBuilder();

        // Mock del System.out para capturar la salida
        System.setOut(new java.io.PrintStream(new java.io.ByteArrayOutputStream()) {
            @Override
            public void println(String x) {
                output.append(x).append("\n");
            }
        });

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

        T2.start();
        T1.start();

        // Esperamos un poco para que las hebras terminen de ejecutarse
        Thread.sleep(1000);

        // Verificamos el orden de las letras
        String outputString = output.toString();
        assertTrue(outputString.indexOf("A") < outputString.indexOf("F"));
        assertTrue(outputString.indexOf("F") < outputString.indexOf("C"));
    }
}
