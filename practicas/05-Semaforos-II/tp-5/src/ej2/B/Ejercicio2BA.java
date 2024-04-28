package ej2.B;

import java.util.concurrent.Semaphore;

public class Ejercicio2BA {

    private static int NPersonas = 4;

    public static void main(String[] args) throws InterruptedException {
        Semaphore permitirSubirPasajeros = new Semaphore(1);
        Semaphore permitirEmpezarViaje = new Semaphore(0); // Controla cuando el bote puede empezar el viaje
        Semaphore permitirBajarPasajeros = new Semaphore(0);
        Semaphore permitirRegreso = new Semaphore(0); // Controla el regreso del bote sin pasajeros


        Thread bote = new Thread(() -> {
            while (true) {
                permitirEmpezarViaje.acquireUninterruptibly(); // Espera permiso para empezar el viaje
                System.out.println("El bote está cruzando hacia la costa Oeste");
                tiempoDeEspera(); // Simula el tiempo de viaje
                System.out.println("El bote ha llegado a la costa Oeste");

                permitirBajarPasajeros.release(); // Permite que la persona baje del bote
                permitirRegreso.acquireUninterruptibly(); // Espera permiso para regresar a la costa Este
                System.out.println("El bote está cruzando de regreso hacia la costa Este");
                tiempoDeEspera(); // Simula el tiempo de viaje de regreso
                System.out.println("El bote ha llegado a la costa Este");
                permitirSubirPasajeros.release(); // Permite que la siguiente persona suba al bote
            }
        });

        for (int i=0; i<NPersonas;i++) {
            final int personaId = i;
            Thread persona = new Thread(() -> {
                permitirSubirPasajeros.acquireUninterruptibly(); // Espera su turno para subir al bote
                System.out.println("Persona " + personaId + " subiendo al bote");
                tiempoDeEspera(); // Simula el tiempo para acomodarse
                permitirEmpezarViaje.release(); // Notifica que el pasajero está listo y el bote puede empezar el viaje

                permitirBajarPasajeros.acquireUninterruptibly(); // Espera llegar para bajar
                System.out.println("Persona " + personaId + " bajando del bote");
                tiempoDeEspera(); // Simula el tiempo para bajar
                permitirRegreso.release(); // Notifica que ha bajado y el bote puede regresar
            });
            persona.start();
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