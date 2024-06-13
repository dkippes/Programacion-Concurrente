package ej2;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        for (int i = 0; i < 10; i++) {
            int random = new Random().nextInt();

            if (random % 2 == 0) {
                new Thread(() -> {
                    try {
                        System.out.println("Acquire: Contador " + semaphore.contador);
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            } else {
                new Thread(() -> {
                    System.out.println("Release: Contador " + semaphore.contador);
                    semaphore.release();
                }).start();
            }
        }
    }
}
