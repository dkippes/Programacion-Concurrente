package practico;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Channel {
    private BlockingQueue<Object> queue;

    public Channel() {
        this.queue = new LinkedBlockingQueue<>();
    }

    public void send(Object c) {
        try {
            queue.put(c);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public Object receive() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
