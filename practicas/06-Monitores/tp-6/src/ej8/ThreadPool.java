package ej8;

import ej3.Buffer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ThreadPool {
    private BufferT buffer;
    private int workers;
    private List<Thread> workersList = new ArrayList<>();
    private int activeWorkers;

    public ThreadPool(BufferT buffer, int workers) {
        this.buffer = buffer;
        this.workers = workers;
        this.activeWorkers = workers;
    }

    synchronized void launch() {
        for (int i= 0; i < workers; i++) {
            Worker worker = new Worker(i, buffer, this);
            Thread thread = new Thread(worker);
            workersList.add(thread);
            thread.start();
        }
        System.out.println("Termino de agregar workers a la lista");
    }

    synchronized void stop() {
        System.out.println("Activando Poison Pills");
        for (int i = 0; i < workers; i++) {
            try {
                buffer.producir(new PoisonPill(i));
            } catch (InterruptedException e) {
            }
        }

        while (activeWorkers > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

    synchronized void workerDone() {
        activeWorkers--;
        if (activeWorkers == 0) {
            notifyAll();
        }
    }
}
