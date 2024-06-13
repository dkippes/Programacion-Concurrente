package ej8;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        int tasks = 10;
        BufferT buffer = new BufferT(tasks);
        for (int i=0; i < tasks; i++) {
            buffer.producir(new DummyTask(i));
        }
        ThreadPool threadPool = new ThreadPool(buffer, 8);
        threadPool.launch();
        threadPool.stop();
    }
}
