package com.eliasheredia.models.barcos.jugador2;

public class Crucero2 {
    public String nombre;
    public int tamano;
    public boolean hundido;

    public Crucero2() {
        this.nombre = "Crucero ruso";
        this.tamano = 4;
        this.hundido = false;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }
}
