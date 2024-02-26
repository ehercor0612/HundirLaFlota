package com.eliasheredia.models.barcos.jugador2;

public class Crucero2 {
    public String nombre;
    public static final int tamano = 4;
    public boolean hundido;

    public Crucero2() {
        this.nombre = "Crucero ruso";
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
