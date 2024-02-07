package com.eliasheredia.models.barcos.jugador1;

public class Submarino1 {
    String nombre;
    int tamano;
    boolean hundido;

    public Submarino1() {
        this.nombre = "Submarino americano";
        this.tamano = 3;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
