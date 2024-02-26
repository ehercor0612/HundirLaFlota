package com.eliasheredia.models.barcos.jugador2;

public class Barca2 {
    public String nombre;
    public static final int tamano = 1;
    public boolean hundido;

    public Barca2() {
        this.nombre = "Barca gatuna 2";
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
