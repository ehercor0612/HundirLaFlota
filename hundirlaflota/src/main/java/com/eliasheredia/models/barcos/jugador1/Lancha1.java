package com.eliasheredia.models.barcos.jugador1;

public class Lancha1 {
    public String nombre;
    public static final int tamano = 2;
    public boolean hundido;

    public Lancha1() {
        this.nombre = "Lancha americana";
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
