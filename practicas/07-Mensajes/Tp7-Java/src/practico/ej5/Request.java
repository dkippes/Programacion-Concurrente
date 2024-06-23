package practico.ej5;

import practico.Channel;

public class Request {
    public int numero;
    public Channel channelPrivado;

    public Request(int numero, Channel channelPrivado) {
        this.numero = numero;
        this.channelPrivado = channelPrivado;
    }
}
