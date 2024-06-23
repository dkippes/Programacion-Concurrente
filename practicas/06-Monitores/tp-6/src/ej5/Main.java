package ej5;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Event event = new Event();

        for (int i = 0; i < 10; i++) {
            int random = new Random().nextInt();
            if (random % 2 == 0) {
                new Suscriptor(event).start();
            } else {
                new Publicador(event).start();
            }
        }
    }
}
