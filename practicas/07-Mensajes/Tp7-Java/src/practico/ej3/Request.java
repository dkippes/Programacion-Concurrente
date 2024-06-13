package practico.ej3;

import practico.Channel;

public class Request {
    public Integer numero;
    public Channel channelPrivado;

    public Request(Integer numero, Channel channelPrivado) {
        this.numero = numero;
        this.channelPrivado = channelPrivado;
    }
}
