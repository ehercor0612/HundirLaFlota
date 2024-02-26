package com.eliasheredia.models.barcos.jugador2;

public class Submarino2 {
    public String nombre;
    public static final int tamano = 3;
    public boolean hundido;

    public Submarino2() {
        this.nombre = "Submarino ruso";
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
