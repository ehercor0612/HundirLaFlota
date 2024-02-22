package com.eliasheredia.models.barcos.jugador1;

public class Lancha1 {
    public String nombre;
    public int tamano;
    public boolean hundido;

    public Lancha1() {
        this.nombre = "Lancha americana";
        this.tamano = 2;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
