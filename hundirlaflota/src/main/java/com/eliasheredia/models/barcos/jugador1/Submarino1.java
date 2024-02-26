package com.eliasheredia.models.barcos.jugador1;

public class Submarino1 {
    public String nombre;
    public static final int tamano = 3;
    public boolean hundido;

    public Submarino1() {
        this.nombre = "Submarino gatuno 1";
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
