package com.eliasheredia.models.barcos.jugador2;

public class Barca2 {
    String nombre;
    int tamano;
    boolean hundido;

    public Barca2() {
        this.nombre = "Barca rusa";
        this.tamano = 1;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}