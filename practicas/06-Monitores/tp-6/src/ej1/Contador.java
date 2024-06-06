package ej1;

class Contador {
    int valor = 0;

    synchronized void incrementar() {
        valor++;
    }

    synchronized void decrementar() {
        valor--;
    }
}
