package practico.ej4.b;

import practico.Channel;

public class Request {
    public String msg;
    public Channel channelPrivado;

    public Request(String msg, Channel channelPrivado) {
        this.msg = msg;
        this.channelPrivado = channelPrivado;
    }
}
