package com.eliasheredia.models.barcos.jugador1;

public class Lancha1 {
    String nombre;
    int tamano;
    boolean hundido;

    public Lancha1() {
        this.nombre = "Lancha americana";
        this.tamano = 2;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
