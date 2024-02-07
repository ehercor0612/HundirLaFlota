package com.eliasheredia.models.barcos.jugador2;

public class Submarino2 {
    String nombre;
    int tamano;
    boolean hundido;

    public Submarino2() {
        this.nombre = "Submarino ruso";
        this.tamano = 3;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
