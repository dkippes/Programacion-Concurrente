package ej4;

import ej3.Buffer;
import ej3.Consumidor;
import ej3.Productor;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Barrera barrera = new Barrera(3);

        Thread t1 = new Thread(() -> {
            System.out.println("a");
            try {
                barrera.esperar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("1");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("b");
            try {
                barrera.esperar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("2");
        });

        Thread t3 = new Thread(() -> {
            System.out.println("c");
            try {
                barrera.esperar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("3");
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
