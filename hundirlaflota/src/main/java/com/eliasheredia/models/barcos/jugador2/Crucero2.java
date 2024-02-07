package com.eliasheredia.models.barcos.jugador2;

public class Crucero2 {
    String nombre;
    int tamano;
    boolean hundido;

    public Crucero2() {
        this.nombre = "Crucero ruso";
        this.tamano = 4;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
