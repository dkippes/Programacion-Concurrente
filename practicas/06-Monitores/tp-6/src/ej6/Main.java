package ej6;

public class Main {
    public static void main(String[] args) {
        Promise promise = new Promise();
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            promise.set(5);
        });
        Thread t2 = new Thread(() -> {
            promise.get();
        });

        t1.start();
        t2.start();
    }
}
