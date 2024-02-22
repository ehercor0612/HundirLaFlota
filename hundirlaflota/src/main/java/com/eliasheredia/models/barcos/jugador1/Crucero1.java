package com.eliasheredia.models.barcos.jugador1;

public class Crucero1 {
    public String nombre;
    public int tamano;
    public boolean hundido;

    public Crucero1() {
        this.nombre = "Crucero americano";
        this.tamano = 4;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
