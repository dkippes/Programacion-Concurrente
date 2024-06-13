package ej8;

import ej3.Buffer;

public class DummyTask implements Runnable {
    private int id;

    public DummyTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task " + id + " is running");
    }
}
