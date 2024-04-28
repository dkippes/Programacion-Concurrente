package teoria;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class Productor1Consumidor1Buffer1 {
    public static void main(String[] args) throws InterruptedException {
        final int[] buffer = {0};
        Semaphore permitirProduccion = new Semaphore(1);
        Semaphore permitirConsumir = new Semaphore(0);

        Thread productor = new Thread(() -> {
            while (true) {
                permitirProduccion.acquireUninterruptibly();
                buffer[0] = 1; // Produce el producto
                System.out.println("Produciendo...");
                accionEspera();
                permitirConsumir.release();
            }
        });

        Thread consumidor = new Thread(() -> {
            while (true) {
                permitirConsumir.acquireUninterruptibly();
                buffer[0] = 0; // Consume el producto
                System.out.println("Consumiendo...");
                accionEspera();
                permitirProduccion.release();
            }
        });

        productor.start();
        consumidor.start();
    }

    private static void accionEspera() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
