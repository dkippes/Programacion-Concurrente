package teoria;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

public class ProductorNConsumidorNBufferN {

    private static int N = 3;
    public static void main(String[] args) throws InterruptedException {
        final int[] buffer = new int[N];
        Semaphore permitirProduccion = new Semaphore(N);
        Semaphore permitirConsumir = new Semaphore(0);
        Semaphore mutexProductores = new Semaphore(1);
        Semaphore mutexConsumidor = new Semaphore(1);
        final int[] inicio = {0};
        final int[] fin = {0};

        for (int i=0; i < N; i++) {
            Thread productor = new Thread(() -> {
                while (true) {
                    permitirProduccion.acquireUninterruptibly();
                    mutexProductores.acquireUninterruptibly();
                    buffer[inicio[0]] = producir();
                    inicio[0] = (inicio[0] +1) % N;
                    mutexProductores.release();
                    permitirConsumir.release();
                }
            });
            productor.start();
        }

        for (int i=0; i < N; i++) {
            Thread consumidor = new Thread(() -> {
                while (true) {
                    permitirConsumir.acquireUninterruptibly();
                    mutexConsumidor.acquireUninterruptibly();
                    buffer[fin[0]] = consumir();
                    fin[0] = (fin[0] + 1) % N;
                    mutexConsumidor.release();
                    permitirProduccion.release();
                }
            });
            consumidor.start();
        }
    }

    private static int producir() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Produciendo...");
        return 1;
    }

    private static int consumir() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Consumiendo...");
        return 0;
    }

}
