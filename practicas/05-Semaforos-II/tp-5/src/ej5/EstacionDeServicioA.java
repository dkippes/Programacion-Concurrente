package ej5;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class EstacionDeServicioA {

    public static void main(String[] args) throws InterruptedException {
        Semaphore puestoAuto = new Semaphore(6, true);
        Semaphore puestoCamion = new Semaphore(1, true);

        while (true) {
            int n = new Random().nextInt(2);
            if (n % 2 == 0) {
                Thread auto = new Thread(() -> {
                    puestoAuto.acquireUninterruptibly();
                    System.out.println("Auto cargando combustible, hay en puesto de carga " + puestoAuto.availablePermits() + " autos cargando combustible");
                    tiempoDeEspera(1000);
                    System.out.println("Auto se va de la estacion de servicio");
                    puestoAuto.release();
                });
                auto.start();
            } else {
                Thread camion = new Thread(() -> {
                    puestoCamion.acquireUninterruptibly();
                    System.out.println("Camion cargando combustible, hay en puesto de abastecimiento " + puestoCamion.availablePermits() + " camiones cargando combustible");
                    tiempoDeEspera(3000);
                    System.out.println("Camion se va de la estacion de servicio");
                    puestoCamion.release();
                });
                camion.start();
            }
            tiempoDeEspera(100);
        }
    }

    static void tiempoDeEspera(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Los roles activos en la estación de servicio son los autos y los camiones.
     * Los recursos compartidos son los puestos de carga de combustible.
     */
}