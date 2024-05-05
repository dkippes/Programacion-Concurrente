package ej3;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Gym {

    public static void main(String[] args) throws InterruptedException {
        // 4 aparatos (grupo muscular distinto)
        // Se le cargan discos a los aparatos
        // 20 discos en total en el gym
        // Clientes tienen una rutina = [ej(aparato 1, 5 discos), ej(aparato 2, 6 discos)]
        // Termina de usar los discos y los deja para que otro los use
        Ejercicio ejercicio1 = new Ejercicio(0, 5);
        Ejercicio ejercicio2 = new Ejercicio(2, 7);
        Ejercicio ejercicio3 = new Ejercicio(1, 6);
        Ejercicio ejercicio4 = new Ejercicio(3, 15);
        Ejercicio ejercicio5 = new Ejercicio(0, 10);
        Ejercicio[] rutina1 = {ejercicio1, ejercicio2, ejercicio3};
        Ejercicio[] rutina2 = {ejercicio4, ejercicio5};
        Ejercicio[] rutina3 = {ejercicio1, ejercicio2, ejercicio3, ejercicio4, ejercicio5};
        Cliente cliente1 = new Cliente(rutina1);
        Cliente cliente2 = new Cliente(rutina2);
        Cliente cliente3 = new Cliente(rutina3);
        Cliente[] clientes = {cliente1, cliente2, cliente3};

        Semaphore discos = new Semaphore(20, true);
        List<Semaphore> aparatos = List.of(new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1));


        for (int i = 0; i < clientes.length; i++) {
            Cliente cliente = clientes[i];
            int finalI = i;
            Thread c = new Thread(() -> {
                for (int j = 0; j < cliente.ejercicios.length; j++) {
                    Ejercicio ejercicio = cliente.ejercicios[j];
                    aparatos.get(ejercicio.aparato).acquireUninterruptibly();
                    discos.acquireUninterruptibly(ejercicio.discos);
                    System.out.println("Cliente " + finalI + " usando aparato " + ejercicio.aparato + " con " + ejercicio.discos + " discos");
                    tiempoDeEspera();
                    discos.release(ejercicio.discos);
                    aparatos.get(ejercicio.aparato).release();
                    System.out.println("Cliente " + finalI + " termino de usar aparato " + ejercicio.aparato + " con " + ejercicio.discos + " discos\n");
                }
                System.out.println("Cliente " + finalI + " salio del gym\n-----------------------------------");
            });
            c.start();
        }
    }

    static void tiempoDeEspera() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * a) Indique cuales son los recursos compartidos y roles activos
     * Los recursos son los discos, 20 permisos de semaforo y los aparatos que son
     * 4 permisos de otro semaforo.
     * Los roles activos son los clientes que interactuan con los discos y aparatos
     * */
}