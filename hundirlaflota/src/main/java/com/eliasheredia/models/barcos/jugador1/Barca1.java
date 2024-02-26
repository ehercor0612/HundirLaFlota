package com.eliasheredia.models.barcos.jugador1;

public class Barca1 {
    public String nombre;
    public static final int tamano = 1;
    public boolean hundido;

    public Barca1() {
        this.nombre = "Barca gatuna 1";
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
