package com.eliasheredia.models.barcos.jugador1;

public class Crucero1 {
    public String nombre;
    public static final int tamano = 4;
    public boolean hundido;

    public Crucero1() {
        this.nombre = "Crucero gatuno 1";
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
