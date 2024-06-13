package practico.ej1.b;

import practico.Channel;

import java.util.Random;

// Proceso P1
class Cliente implements Runnable {
    private Channel channel;

    public Cliente(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            Channel cprivado = new Channel();
            Integer random = new Random().nextInt(10);
            String tipo = null;
            if (random % 2 == 0) {
                tipo = "cuenta";
            } else {
                tipo = "sigue";
            }
            Request req = new Request(tipo, cprivado);

            channel.send(req);
            if (tipo.equals("cuenta")) {
                Integer cantidad = null;
                cantidad = (Integer) cprivado.receive();
                System.out.println(cantidad);
            }
        }
    }
}

