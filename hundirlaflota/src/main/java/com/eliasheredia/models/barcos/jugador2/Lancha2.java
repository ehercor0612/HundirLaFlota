package com.eliasheredia.models.barcos.jugador2;

public class Lancha2 {
    public String nombre;
    public static final int tamano = 2;
    public boolean hundido;

    public Lancha2() {
        this.nombre = "Lancha rusa";
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
