package ej3;

import ej2.Semaphore;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer(3);
        for (int i = 0; i < 10; i++) {
            int random = new Random().nextInt();

            if (random % 2 == 0) {
                new Consumidor(buffer).start();
            } else {
                new Productor(buffer).start();
            }
        }
    }
}
