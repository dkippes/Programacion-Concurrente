package ej8;

import ej3.Buffer;

public class Worker implements Runnable {
    private int id;
    private BufferT buffer;
    private ThreadPool pool;

    public Worker(int id, BufferT buffer, ThreadPool pool) {
        this.id = id;
        this.buffer = buffer;
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Runnable task = buffer.consumir();
                task.run();
            }
        } catch (PoisonException e) {
            System.out.println("Worker " + id + " Poison Pill, terminando.");
            pool.workerDone();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
