package practico.ej1.b;

import practico.Channel;

public class Request {
    public String tipo;
    public Channel channel;

    public Request(String tipo, Channel channel) {
        this.tipo = tipo;
        this.channel = channel;
    }
}
