package practico.ej1.a;

import practico.Channel;

// Proceso P1
class Servidor implements Runnable {
    private Channel channel;

    public Servidor(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        int cantidad = 0;
        while (true) {
            String tipo;
            tipo = (String) channel.receive();

            if (tipo.equals("cuenta")) {
                System.out.println(cantidad);
                cantidad = 0;
            }
            if (tipo.equals("sigue")) {
                cantidad++;
            }
        }
    }
}

