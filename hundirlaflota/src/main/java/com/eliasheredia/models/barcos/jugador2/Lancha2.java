package com.eliasheredia.models.barcos.jugador2;

public class Lancha2 {
    public String nombre;
    public int tamano;
    public boolean hundido;

    public Lancha2() {
        this.nombre = "Lancha rusa";
        this.tamano = 2;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
