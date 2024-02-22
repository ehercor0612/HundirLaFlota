package com.eliasheredia.models.barcos.jugador1;

public class Submarino1 {
    public String nombre;
    public int tamano;
    public boolean hundido;

    public Submarino1() {
        this.nombre = "Submarino americano";
        this.tamano = 3;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
