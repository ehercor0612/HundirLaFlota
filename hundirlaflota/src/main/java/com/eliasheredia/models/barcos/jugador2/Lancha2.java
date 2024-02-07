package com.eliasheredia.models.barcos.jugador2;

public class Lancha2 {
    String nombre;
    int tamano;
    boolean hundido;

    public Lancha2() {
        this.nombre = "Lancha rusa";
        this.tamano = 2;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
