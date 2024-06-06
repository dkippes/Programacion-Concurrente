package ej1;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Contador contador = new Contador();

        Thread incrementador = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                contador.incrementar();
            }
        });

        Thread decrementador = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                contador.decrementar();
            }
        });

        incrementador.start();
        decrementador.start();

        sleep(1000);
        System.out.println("Valor final del contador: " + contador.valor);
    }
}