package ej2.B;

import java.util.concurrent.Semaphore;

public class Ejercicio2BB {

    private static int N = 4;

    public static void main(String[] args) throws InterruptedException {
        // Bote
        Semaphore viajarEste = new Semaphore(0);
        Semaphore viajarOeste = new Semaphore(0);
        // Persona
        Semaphore subirOeste = new Semaphore(N);
        Semaphore bajarEste = new Semaphore(0);
        Semaphore subirEste = new Semaphore(0);
        Semaphore bajarOeste = new Semaphore(0);


        Thread bote = new Thread(() -> {
            while (true) {
                viajarEste.acquireUninterruptibly(N);
                System.out.println("Bote viajando al oeste");
                System.out.println("Amarando en el oeste");
                tiempoDeEspera();

                bajarEste.release(N);
                viajarOeste.acquireUninterruptibly(N);
                System.out.println("Bote viajando al este");
                System.out.println("Amarando en el este");
                tiempoDeEspera();
                bajarOeste.release(N);
            }
        });

        // Persona del oeste
        for (int i=0; i<8;i++) {
            final int personaId = i;
            new Thread(() -> {
                subirOeste.acquireUninterruptibly();
                System.out.println("Persona " + personaId + " subió al bote");
                viajarEste.release();

                bajarEste.acquireUninterruptibly();
                System.out.println("Persona " + personaId + " bajó del bote");
                subirEste.release();
            }).start();
        }

        // Persona del este
        for (int i=20; i<28;i++) {
            final int personaId = i;
            new Thread(() -> {
                subirEste.acquireUninterruptibly();
                System.out.println("Persona " + personaId + " subió al bote");
                viajarOeste.release();

                bajarOeste.acquireUninterruptibly();
                System.out.println("Persona " + personaId + " bajó del bote");
                subirOeste.release();
            }).start();
        }

        bote.start();
    }

    static void tiempoDeEspera() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}