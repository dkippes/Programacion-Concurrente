package ej5;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class EstacionDeServicioB {

    public static void main(String[] args) throws InterruptedException {
        // Auto
        Semaphore mutexA = new Semaphore(1, true);
        Semaphore mutexP = new Semaphore(1, true);
        Semaphore permisoAuto = new Semaphore(6, true);
        Semaphore permisoA = new Semaphore(1, true);
        AtomicInteger autos = new AtomicInteger();

        // Camion
        Semaphore mutextC = new Semaphore(1, true);
        Semaphore permisoC = new Semaphore(1, true);
        AtomicInteger camiones = new AtomicInteger();


        for (int i = 0; i < 100; i++) {
            int n = new Random().nextInt(2);
            if (n % 2 == 0) {
                Thread auto = new Thread(() -> {
                    mutexP.acquireUninterruptibly(); // Mutex de prioridad
                    permisoA.acquireUninterruptibly(); // Pide permiso para entrar a la estacion de servicio
                    mutexA.acquireUninterruptibly(); // Seccion critica de suma
                    autos.getAndIncrement();
                    if (autos.get() == 1) {
                        permisoC.acquireUninterruptibly(); // Roba permiso a los camiones (1 solo permiso)
                    }
                    mutexA.release(); // Fin seccion critica
                    permisoA.release(); // Libera el permiso para que otro auto pueda entrar a la estacion de servicio
                    mutexP.release();

                    permisoAuto.acquireUninterruptibly(); // Fila para cargar combustible
                    System.out.println("Auto cargando combustible, hay en puesto de carga " + permisoAuto.availablePermits() + " autos cargando combustible");
                    //tiempoDeEspera(1500);
                    permisoAuto.release(); // Libera el puesto de carga

                    System.out.println("Auto se va de la estacion de servicio " + permisoAuto.availablePermits());

                    mutexA.acquireUninterruptibly(); // Seccion critica de resta
                    autos.getAndDecrement();
                    if (autos.get() == 0) {
                        permisoC.release(); // Devuelve permiso a los camiones
                    }
                    mutexA.release(); // Fin seccion critica
                });
                auto.start();
            } else {
                Thread camion = new Thread(() -> {
                    //tiempoDeEspera(3000);
                    mutextC.acquireUninterruptibly(); // Seccion critica de suma
                    camiones.getAndIncrement();
                    if (camiones.get() == 1) {
                        System.out.println("Roba permisos a autos " + permisoAuto.availablePermits() + " autos cargando combustible");
                        permisoA.acquireUninterruptibly(); // Roba 1 permiso a los autos (No permite que entren autos a la estacion de servicio)
                    }
                    mutextC.release(); // Fin seccion critica

                    permisoC.acquireUninterruptibly(); // Pide permiso para cargar combustible
                    System.out.println("Camion cargando combustible, hay en puesto de abastecimiento");
                    //tiempoDeEspera(3000);
                    permisoC.release(); // Libera el puesto de carga
                    System.out.println("Camion se va de la estacion de servicio");

                    mutextC.acquireUninterruptibly(); // Seccion critica de resta
                    camiones.getAndDecrement();
                    if (camiones.get() == 0) {
                        permisoA.release();
                    }
                    mutextC.release(); // Fin seccion critica
                });
                camion.start();
            }
            //tiempoDeEspera(100);
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
     * No va a ser libre de inanicion ya que si vienen camiones indefinidamente siempre le
     * va a robar el puesto de carga a los autos por lo cual generaria starvation.
     */
}