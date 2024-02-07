package com.eliasheredia.models.barcos.jugador1;

public class Barca1 {
    String nombre;
    int tamano;
    boolean hundido;

    public Barca1() {
        this.nombre = "Barca americana";
        this.tamano = 1;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
