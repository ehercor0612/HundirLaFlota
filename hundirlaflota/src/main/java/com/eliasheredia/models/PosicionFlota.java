package com.eliasheredia.models;

public class PosicionFlota {

    private int posicionX;
    private int posicionY;
    private EstadosPosicion estado;

    public PosicionFlota(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public int getFila() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getColumna() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public EstadosPosicion getEstado() {
        return estado;
    }

    public void setEstado(EstadosPosicion estado) {
        this.estado = estado;
    }
}
