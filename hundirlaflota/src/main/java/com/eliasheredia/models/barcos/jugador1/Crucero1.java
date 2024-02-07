package com.eliasheredia.models.barcos.jugador1;

public class Crucero1 {
    String nombre;
    int tamano;
    boolean hundido;

    public Crucero1() {
        this.nombre = "Crucero americano";
        this.tamano = 4;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
